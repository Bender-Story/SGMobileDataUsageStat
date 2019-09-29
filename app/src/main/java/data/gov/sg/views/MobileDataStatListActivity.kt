package data.gov.sg.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import data.gov.sg.R
import data.gov.sg.components.ActivityUIExt
import data.gov.sg.components.visible
import data.gov.sg.viewmodels.MobileDataStatListViewModel
import data.gov.sg.viewmodels.YearlyDataCosumptionRowViewModel
import kotlinx.android.synthetic.main.activity_mobile_data_stat_list.*

class MobileDataStatListActivity : AppCompatActivity() {
    var viewModel: MobileDataStatListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_data_stat_list)
        viewModel = ViewModelProviders.of(this).get(MobileDataStatListViewModel::class.java)
        observeList()
        fetchData()
    }

    private fun fetchData(){
        progressbar.visibility=View.VISIBLE
        viewModel?.getMobileDataList (
            {progressbar.visibility=View.GONE},
            { ActivityUIExt(this).buildDialog(it){
                progressbar.visibility=View.GONE
                fetchData()
            }
        })
    }

    // Observers th mutable list to update recycler view
    private fun observeList() {
        viewModel?.yearlyConsumptionData?.observe(this,
            Observer {
                setRecyclerView(it)
            })
    }


    private fun setRecyclerView(list:List<YearlyDataCosumptionRowViewModel>?){
        recyclerView.adapter=MobileDataStatsAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
