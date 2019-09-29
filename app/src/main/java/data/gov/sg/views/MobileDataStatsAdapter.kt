package data.gov.sg.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import data.gov.sg.databinding.YearlyDataConsumptionRowBinding
import data.gov.sg.viewmodels.YearlyDataCosumptionRowViewModel

// Related list adapter
class MobileDataStatsAdapter(val items: List<YearlyDataCosumptionRowViewModel>?) :
    RecyclerView.Adapter<MobileDataStatsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = data.gov.sg.databinding.YearlyDataConsumptionRowBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position)!!)

    inner class ViewHolder(val binding: YearlyDataConsumptionRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: YearlyDataCosumptionRowViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }
}