package data.gov.sg.viewmodels

import data.gov.sg.data.YearlyConsumptionModel

class YearlyDataCosumptionRowViewModel(
    val data: YearlyConsumptionModel?,
    private val onSelect: (YearlyConsumptionModel) -> Unit
){
    fun onClick() {
        data?.let { onSelect.invoke(it) }
    }
}