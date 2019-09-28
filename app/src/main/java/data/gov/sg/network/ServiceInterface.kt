package data.gov.sg.network

import data.gov.sg.data.MobileDataConsumptionModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceInterface{
    @GET("action/datastore_search")
    fun fetchMobileDataStats(@Query("resource_id")resourceId :String): Observable<MobileDataConsumptionModel>
}