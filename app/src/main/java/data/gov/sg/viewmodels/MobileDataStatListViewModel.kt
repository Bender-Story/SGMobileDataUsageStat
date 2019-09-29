package data.gov.sg.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.gov.sg.Constants.RESOURCE_ID
import data.gov.sg.data.Record
import data.gov.sg.data.YearlyData
import data.gov.sg.database.AppDatabase
import data.gov.sg.network.AppServiceRepo
import data.gov.sg.utils.toFormatDouble

class MobileDataStatListViewModel: ViewModel() {
    // related movies list.
    val yearlyConsumptionData: MutableLiveData<List<YearlyDataCosumptionRowViewModel>?> = MutableLiveData()
    private val appServiceRepo = AppServiceRepo()

    fun getMobileDataList(onSuccess: () -> Unit,onError: (String) -> Unit){
      appServiceRepo.getMobileDataList(RESOURCE_ID,{
          onSuccess.invoke()
           yearlyConsumptionData.postValue( extraRequiredData(it.result.records))
      },{
        onError.invoke(it)
      })
    }

    fun addDataToLocalDatabase(db: AppDatabase?){
        db?.yearlyDataDao()?.deleteAll()
        yearlyConsumptionData.value?.forEach {
            db?.yearlyDataDao()?.insertAll(it.data)
        }
    }

    fun getDataFromLocalDataBase(db: AppDatabase?){
        var data=db?.yearlyDataDao()?.getAll()
       var viewModelData= data?.map {
            YearlyDataCosumptionRowViewModel(it){}
        }
        yearlyConsumptionData.postValue(viewModelData)
    }

    fun extraRequiredData(records: List<Record>) :List<YearlyDataCosumptionRowViewModel>{
      val sample=  records.groupBy { it.quarter.substring(0,4) }

        return sample.map { records ->
            val list= records.value
            val total= list.sumByDouble { it.volume_of_mobile_data.toDouble() }.toFormatDouble()

            var decrease=false
           list.forEachIndexed { index, record ->
               if(list.size>index+1) {
                   decrease = (list[index].volume_of_mobile_data.toDouble() >
                           list[index + 1].volume_of_mobile_data.toDouble())|| decrease

                   if (decrease) return@forEachIndexed
               }

           }

            YearlyDataCosumptionRowViewModel(YearlyData(records.key,total,decrease)){}
         }
    }
}
