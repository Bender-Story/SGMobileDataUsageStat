package data.gov.sg.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import data.gov.sg.R
import data.gov.sg.viewmodels.MobileDataStatListViewModel

class MobileDataStatListActivity : AppCompatActivity() {
    var viewModel: MobileDataStatListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_data_stat_list)
        viewModel = ViewModelProviders.of(this).get(MobileDataStatListViewModel::class.java)
        fetchData()
    }

    private fun fetchData(){
        viewModel?.getMobileDataList()
    }


}
