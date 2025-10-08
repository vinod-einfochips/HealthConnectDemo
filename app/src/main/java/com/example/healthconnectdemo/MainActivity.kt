package com.example.healthconnectdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.lifecycle.lifecycleScope
import com.example.healthconnectdemo.databinding.ActivityMainBinding
import com.example.healthconnectdemo.healthconnect.HealthConnectManager
import com.example.healthconnectdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var healthConnectManager: HealthConnectManager

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Check if Health Connect is available
        if (HealthConnectClient.getSdkStatus(this) != HealthConnectClient.SDK_AVAILABLE) {
            showHealthConnectNotAvailableDialog()
            return
        }

        setupUI()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        // Check permissions when user returns from Health Connect app
        viewModel.checkPermissions()
    }

    private fun requestHealthConnectPermissions() {
        requestPermissions()
    }

    private fun requestPermissions() {
        if (healthConnectManager.checkHealthConnectIsAvailable()) {
            showInstallHealthConnectPopup()
        } else {
            lifecycleScope.launch {
                try {
                    val granted = healthConnectManager.hasAllPermissions()
                    if (granted) {
                        Log.d(TAG, "All permissions already granted!")
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.permissions_already_granted),
                            Toast.LENGTH_SHORT,
                        ).show()
                        viewModel.checkPermissions()
                    } else {
                        Log.d(TAG, "Requesting permissions...")
                        permissionLauncher.launch(healthConnectManager.permissions)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error checking permissions", e)
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error_checking_permissions),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }

    private val permissionLauncher =
        registerForActivityResult(PermissionController.createRequestPermissionResultContract()) { result ->
            if (result.containsAll(healthConnectManager.permissions)) {
                Log.d(TAG, "Permissions granted successfully!")
                Toast.makeText(
                    this,
                    getString(R.string.permissions_granted_success),
                    Toast.LENGTH_SHORT,
                ).show()
                viewModel.checkPermissions()
            } else {
                Log.e(TAG, "Permissions denied")
                showEnableHealthPermissionPopup()
            }
        }

    private fun showInstallHealthConnectPopup() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.health_connect_required))
            .setMessage(getString(R.string.health_connect_install_message))
            .setPositiveButton(getString(R.string.install)) { _, _ ->
                openPlayStoreForHealthConnect()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun showEnableHealthPermissionPopup() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.permissions_required))
            .setMessage(getString(R.string.permissions_required_message))
            .setPositiveButton(getString(R.string.grant_permissions)) { _, _ ->
                requestPermissions()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(
                    this,
                    getString(R.string.permissions_needed_to_continue),
                    Toast.LENGTH_LONG,
                ).show()
            }
            .setCancelable(false)
            .show()
    }

    private fun openPlayStoreForHealthConnect() {
        try {
            val intent =
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("market://details?id=com.google.android.apps.healthdata")
                }
            startActivity(intent)
        } catch (e: Exception) {
            val intent =
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.healthdata")
                }
            startActivity(intent)
        }
    }

    private fun showHealthConnectPermissionDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.health_connect_permissions_title))
            .setMessage(getString(R.string.health_connect_permissions_message))
            .setPositiveButton(getString(R.string.open_health_connect)) { _, _ ->
                openHealthConnectApp()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(
                    this,
                    getString(R.string.health_permissions_required),
                    Toast.LENGTH_LONG,
                ).show()
            }
            .setCancelable(false)
            .show()
    }

    private fun openHealthConnectApp() {
        try {
            // Try to open Health Connect app directly
            val intent =
                Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", "com.google.android.apps.healthdata", null)
                }
            startActivity(intent)

            Toast.makeText(
                this,
                getString(R.string.grant_temperature_permissions),
                Toast.LENGTH_LONG,
            ).show()
        } catch (e: Exception) {
            // Fallback: Try to open Health Connect main screen
            try {
                val healthConnectIntent =
                    packageManager.getLaunchIntentForPackage(
                        "com.google.android.apps.healthdata",
                    )
                if (healthConnectIntent != null) {
                    startActivity(healthConnectIntent)
                    Toast.makeText(
                        this,
                        getString(R.string.navigate_to_permissions),
                        Toast.LENGTH_LONG,
                    ).show()
                } else {
                    showHealthConnectNotInstalledDialog()
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    this,
                    getString(R.string.could_not_open_health_connect, ex.message),
                    Toast.LENGTH_LONG,
                ).show()
            }
        }
    }

    private fun showHealthConnectNotInstalledDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.health_connect_not_found))
            .setMessage(getString(R.string.health_connect_not_installed_message))
            .setPositiveButton(getString(R.string.open_play_store)) { _, _ ->
                openPlayStoreForHealthConnect()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun setupUI() {
        binding.btnRecordTemperature.setOnClickListener {
            val temperatureText = binding.etTemperature.text.toString()

            if (temperatureText.isEmpty()) {
                Toast.makeText(this, getString(R.string.enter_temperature), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val temperature = temperatureText.toDouble()
                viewModel.recordTemperature(temperature)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, getString(R.string.enter_valid_number), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnViewHistory.setOnClickListener {
            val intent = Intent(this, TemperatureHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.btnCheckPermissions.setOnClickListener {
            requestHealthConnectPermissions()
        }
    }

    private fun observeViewModel() {
        viewModel.permissionStatus.observe(this) { hasPermissions ->
            val statusText =
                if (hasPermissions) {
                    getString(R.string.health_connect_granted)
                } else {
                    getString(R.string.health_connect_not_granted)
                }
            binding.tvPermissionStatus.text = statusText

            // Update button text based on permission status
            binding.btnCheckPermissions.text =
                if (hasPermissions) {
                    getString(R.string.permissions_granted_status)
                } else {
                    getString(R.string.grant_permissions)
                }
        }

        viewModel.temperatureRecorded.observe(this) { result ->
            result.onSuccess { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                binding.etTemperature.text?.clear()
            }
            result.onFailure { error ->
                Toast.makeText(
                    this,
                    error.message ?: getString(R.string.error_recording_temperature),
                    Toast.LENGTH_LONG,
                ).show()
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showHealthConnectNotAvailableDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.health_connect_not_available))
            .setMessage(getString(R.string.health_connect_not_available_message))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}
