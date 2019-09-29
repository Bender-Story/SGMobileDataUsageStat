package data.gov.sg.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import data.gov.sg.Constants.RESOURCE_ID
import data.gov.sg.data.Record
import data.gov.sg.data.YearlyConsumptionModel
import data.gov.sg.network.AppServiceRepo
import data.gov.sg.utils.toFormatDouble

class MobileDataStatListViewModel(application: Application) : AndroidViewModel(application) {
    // related movies list.
    val yearlyConsumptionData: MutableLiveData<List<YearlyDataCosumptionRowViewModel>?> = MutableLiveData()
    private val appServiceRepo = AppServiceRepo()

    fun getMobileDataList(onError: (String) -> Unit){
      appServiceRepo.getMobileDataList(RESOURCE_ID,{
           yearlyConsumptionData.postValue( extraRequiredData(it.result.records))
      },{
        onError.invoke(it)
      })
    }

    fun extraRequiredData(records: List<Record>) :List<YearlyDataCosumptionRowViewModel>{
      val sample=  records.groupBy { it.quarter.substring(0,4) }

        return sample.map { records ->
            val list= records.value
            val total= list.sumByDouble { it.volume_of_mobile_data.toDouble() }.toFormatDouble()

            var decrease=false
           list.forEachIndexed { index, record ->
               if(list.size>index+1) {
                   decrease = list[index].volume_of_mobile_data.toDouble() >
                           list[index + 1].volume_of_mobile_data.toDouble()

                   if (decrease) return@forEachIndexed
               }

           }

            YearlyDataCosumptionRowViewModel(YearlyConsumptionModel(records.key,total,decrease)){}
         }
    }
}
