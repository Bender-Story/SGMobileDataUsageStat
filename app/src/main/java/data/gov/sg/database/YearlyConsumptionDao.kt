package data.gov.sg.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import data.gov.sg.data.YearlyData

@Dao
interface YearlyDataDao {
    @Query("SELECT * FROM yearlyData")
    fun getAll(): List<YearlyData>

    @Insert
    fun insertAll(vararg yearlyData: YearlyData?)

    @Delete
    fun delete(yearlyData: YearlyData)

    @Query("DELETE FROM yearlyData")
    fun deleteAll()
}