package com.example.healthconnectdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthconnectdemo.databinding.ItemTemperatureReadingBinding
import com.example.healthconnectdemo.model.TemperatureReading

class TemperatureHistoryAdapter(
    private val onDeleteClick: (TemperatureReading) -> Unit,
) : ListAdapter<TemperatureReading, TemperatureHistoryAdapter.ViewHolder>(
        TemperatureDiffCallback(),
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding =
            ItemTemperatureReadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return ViewHolder(binding, onDeleteClick)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemTemperatureReadingBinding,
        private val onDeleteClick: (TemperatureReading) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reading: TemperatureReading) {
            binding.apply {
                tvTemperatureCelsius.text = reading.getFormattedCelsius()
                tvTemperatureFahrenheit.text = reading.getFormattedFahrenheit()
                tvDateTime.text = reading.getFormattedDateTime()

                // Set temperature status color
                val tempCelsius = reading.temperatureCelsius
                val statusText =
                    when {
                        tempCelsius < 36.1 -> "Low"
                        tempCelsius > 37.2 -> "High"
                        else -> "Normal"
                    }
                tvStatus.text = statusText

                // Delete button click
                btnDelete.setOnClickListener {
                    onDeleteClick(reading)
                }
            }
        }
    }

    private class TemperatureDiffCallback : DiffUtil.ItemCallback<TemperatureReading>() {
        override fun areItemsTheSame(
            oldItem: TemperatureReading,
            newItem: TemperatureReading,
        ): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(
            oldItem: TemperatureReading,
            newItem: TemperatureReading,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
