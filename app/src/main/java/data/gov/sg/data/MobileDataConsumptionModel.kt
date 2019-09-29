package data.gov.sg.data

data class MobileDataConsumptionModel(
    val help: String,
    val result: Result,
    val success: Boolean
)

data class YearlyConsumptionModel(
    val year:String,
    val value:String,
    val decrease :Boolean
)