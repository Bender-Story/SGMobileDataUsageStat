package data.gov.sg.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import data.gov.sg.Constants.RESOURCE_ID
import data.gov.sg.network.AppServiceRepo

class MobileDataStatListViewModel(application: Application) : AndroidViewModel(application) {
    val appServiceRepo = AppServiceRepo()

    fun getMobileDataList(){
      appServiceRepo.getMobileDataList(RESOURCE_ID,{
        if(it.success){}
      },{

      })
    }
}
