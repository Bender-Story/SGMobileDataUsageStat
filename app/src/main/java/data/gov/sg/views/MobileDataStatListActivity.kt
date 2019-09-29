package data.gov.sg.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import data.gov.sg.Constants.GO_OFFLINE
import data.gov.sg.R
import data.gov.sg.components.ActivityUIExt
import data.gov.sg.components.visible
import data.gov.sg.database.AppDatabase
import data.gov.sg.viewmodels.MobileDataStatListViewModel
import data.gov.sg.viewmodels.YearlyDataCosumptionRowViewModel
import kotlinx.android.synthetic.main.activity_mobile_data_stat_list.*
import org.jetbrains.anko.doAsync

class MobileDataStatListActivity : AppCompatActivity() {
    var viewModel: MobileDataStatListViewModel? = null
    var database:AppDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_data_stat_list)
        viewModel = ViewModelProviders.of(this).get(MobileDataStatListViewModel::class.java)
        initDataBase()
        observeList()
        fetchData()

    }

    private fun fetchData(){
        if(intent.extras.getBoolean(GO_OFFLINE))
            fetchLocalData()
        else {
            progressbar.visibility=View.VISIBLE
            viewModel?.getMobileDataList(
                {
                    addData()
                    progressbar.visibility = View.GONE
                },
                {
                    ActivityUIExt(this).buildDialog(it, onReload = {
                        progressbar.visibility = View.GONE
                        fetchData()
                    }, onNegitive = {})
                })
        }
    }

    private fun addData(){
        doAsync {
            viewModel?.addDataToLocalDatabase(database)
        }
    }

    private fun fetchLocalData(){
        doAsync {
            viewModel?.getDataFromLocalDataBase(database)
        }
    }

    // Observers th mutable list to update recycler view
    private fun observeList() {
        viewModel?.yearlyConsumptionData?.observe(this,
            Observer {
                setRecyclerView(it)
            })
    }


    private fun setRecyclerView(list:List<YearlyDataCosumptionRowViewModel>?){
        if(list==null || list.isEmpty()){
            NoDataTextView.visibility=View.VISIBLE
        }else {
            NoDataTextView.visibility=View.GONE
            recyclerView.adapter = MobileDataStatsAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun initDataBase(){
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

}
