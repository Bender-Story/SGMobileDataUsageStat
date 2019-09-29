package data.gov.sg.network

import data.gov.sg.Constants.NEWS_API_BASE_URL
import data.gov.sg.data.MobileDataConsumptionModel

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers

class AppServiceRepo{
    private var serviceInterface: ServiceInterface?=null
    init {
        serviceInterface = NetworkAPIController.getApiClient(NEWS_API_BASE_URL)?.create(ServiceInterface::class.java)
    }
    // gets the DATA from service
    fun getMobileDataList(resourceId:String, onSuccess: (MobileDataConsumptionModel) -> Unit,
                     onError: (String) -> Unit){

        serviceInterface!!.fetchMobileDataStats(resourceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }

}


