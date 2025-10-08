package com.example.healthconnectdemo

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthconnectdemo.adapter.TemperatureHistoryAdapter
import com.example.healthconnectdemo.databinding.ActivityTemperatureHistoryBinding
import com.example.healthconnectdemo.model.TemperatureReading
import com.example.healthconnectdemo.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.temporal.ChronoUnit

@AndroidEntryPoint
class TemperatureHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemperatureHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: TemperatureHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Temperature History"

        setupRecyclerView()
        observeViewModel()
        loadHistory()
    }

    private fun setupRecyclerView() {
        adapter =
            TemperatureHistoryAdapter { reading ->
                showDeleteConfirmationDialog(reading)
            }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TemperatureHistoryActivity)
            adapter = this@TemperatureHistoryActivity.adapter
        }
    }

    private fun showDeleteConfirmationDialog(reading: TemperatureReading) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Delete Temperature Record")
            .setMessage(
                "Are you sure you want to delete this temperature reading?\n\n" +
                    "${reading.getFormattedCelsius()} recorded on ${reading.getFormattedDateTime()}",
            )
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteTemperatureReading(reading.recordId)
                Toast.makeText(this, "Temperature record deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun observeViewModel() {
        viewModel.temperatureHistory.observe(this) { temperatures ->
            if (temperatures.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.emptyView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(temperatures)
            }
            binding.progressBar.visibility = View.GONE
        }

        viewModel.errorMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun loadHistory() {
        // Load last 30 days of data
        val endTime = Instant.now()
        val startTime = endTime.minus(30, ChronoUnit.DAYS)
        viewModel.loadTemperatureHistory(startTime, endTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
