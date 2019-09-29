package data.gov.sg.viewmodels



import data.gov.sg.BaseTest
import data.gov.sg.data.Record
import data.gov.sg.database.AppDatabase
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class MobileDataStatsViewModelTest: BaseTest(){

    var mobileDataStatListViewModel= MobileDataStatListViewModel()



    @Test
    fun `get Mobile Data List`(){
        mobileDataStatListViewModel.getMobileDataList({
            assert(true)
        },{ assert(false)})

    }
    @Test
    fun addDataToLocalDatabase(){

        val appDatabase= Mockito.mock(AppDatabase::class.java)

        Assert.assertNotNull(mobileDataStatListViewModel.addDataToLocalDatabase(appDatabase))
    }

    @Test
    fun getDataFromLocalDataBase(){

        val appDatabase= Mockito.mock(AppDatabase::class.java)

        Assert.assertNotNull(mobileDataStatListViewModel.getDataFromLocalDataBase(appDatabase))
    }

    @Test
    fun addDataToLocalDatabase2(){

        val appDatabase= null

        Assert.assertNotNull(mobileDataStatListViewModel.addDataToLocalDatabase(appDatabase))
    }

    @Test
    fun getDataFromLocalDataBase2(){

        val appDatabase= null

        Assert.assertNotNull(mobileDataStatListViewModel.getDataFromLocalDataBase(appDatabase))
    }

    @Test
    fun `extra Required Data`(){

        var records= listOf(Record(1,"2004-Q1","0.00384"),Record(2,"2004-Q2","0.00456"),Record(3,"2004-Q3","0.00384"),Record(4,"2004-Q4","0.00999"))
       var list= mobileDataStatListViewModel.extraRequiredData(records)

        Assert.
            assertTrue(list[0]?.data?.decrease ?:false)
        Assert.
            assertEquals("2004",list[0]?.data?.year ?:false)
        Assert.
            assertNotNull(list[0]?.data?.value)
    }

    @Test
    fun `extra Required Data from empty records`(){

        var records= listOf<Record>()
        var list= mobileDataStatListViewModel.extraRequiredData(records)
        Assert.
            assertTrue(list.isEmpty())
    }

}