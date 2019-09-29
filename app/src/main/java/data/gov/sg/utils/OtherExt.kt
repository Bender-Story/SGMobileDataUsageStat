package data.gov.sg.utils

fun Double.toFormatDouble(decimalCount: Int = 6): String {
    return String.format("%." + decimalCount + "f", this)
}