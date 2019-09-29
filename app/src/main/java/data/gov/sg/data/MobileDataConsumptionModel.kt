package data.gov.sg.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MobileDataConsumptionModel(
    val help: String,
    val result: Result,
    val success: Boolean
)
@Entity (tableName = "yearlyData")
data class YearlyData(
    @PrimaryKey val year:String,
    @ColumnInfo(name = "value") val value:String,
    @ColumnInfo(name = "decrease") val decrease :Boolean
)

