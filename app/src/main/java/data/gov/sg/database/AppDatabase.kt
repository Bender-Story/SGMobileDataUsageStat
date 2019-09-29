package data.gov.sg.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.gov.sg.data.YearlyData

@Database(entities = arrayOf(YearlyData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun yearlyDataDao(): YearlyDataDao
}