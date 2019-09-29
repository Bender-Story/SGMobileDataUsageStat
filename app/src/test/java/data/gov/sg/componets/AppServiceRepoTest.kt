package data.gov.sg.componets

import data.gov.sg.BaseTest
import data.gov.sg.Constants.RESOURCE_ID
import data.gov.sg.network.AppServiceRepo
import org.junit.Assert
import org.junit.Test

class AppServiceRepoTest:BaseTest(){

    @Test
    fun `get Mobile data stats from server positive scenario`(){
        AppServiceRepo().getMobileDataList(RESOURCE_ID,{
            Assert.assertTrue(it.success)
        },{ assert(false)})
    }
    @Test
    fun `service should fail when resource id is empty`(){
        AppServiceRepo().getMobileDataList("",{
            Assert.assertFalse(it.success)
        },{ assert(true)})
    }
}