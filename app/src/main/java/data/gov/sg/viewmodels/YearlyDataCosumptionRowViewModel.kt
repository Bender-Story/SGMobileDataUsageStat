package data.gov.sg.viewmodels

import data.gov.sg.data.YearlyData

class YearlyDataCosumptionRowViewModel(
    val data: YearlyData?,
    private val onSelect: (YearlyData) -> Unit
){
    fun onClick() {
        data?.let { onSelect.invoke(it) }
    }
}