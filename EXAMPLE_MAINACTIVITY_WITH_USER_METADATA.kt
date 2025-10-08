// Example: How to update MainActivity.kt to include user metadata

// Add this import at the top
import com.example.healthconnectdemo.model.UserInfo
import com.example.healthconnectdemo.model.UserType

// Update the setupUI() method's button click listener:

private fun setupUI() {
    binding.btnRecordTemperature.setOnClickListener {
        val temperatureText = binding.etTemperature.text.toString()
        
        if (temperatureText.isEmpty()) {
            Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }

        try {
            val temperature = temperatureText.toDouble()
            
            // OPTION 1: Simple - Always use SELF as user type
            val userInfo = UserInfo(
                userName = "Self",
                userType = UserType.SELF
            )
            viewModel.recordTemperature(temperature, userInfo)
            
            // OPTION 2: Without user metadata (backward compatible)
            // viewModel.recordTemperature(temperature)
            
            // OPTION 3: With custom user name (you can add an EditText for this)
            // val userName = binding.etUserName.text.toString()
            // if (userName.isNotEmpty()) {
            //     val userInfo = UserInfo(userName, UserType.SELF)
            //     viewModel.recordTemperature(temperature, userInfo)
            // } else {
            //     viewModel.recordTemperature(temperature)
            // }
            
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
        }
    }

    binding.btnViewHistory.setOnClickListener {
        val intent = Intent(this, TemperatureHistoryActivity::class.java)
        startActivity(intent)
    }

    binding.btnCheckPermissions.setOnClickListener {
        if (PermissionHelper.hasAllBodySensorPermissions(this)) {
            requestHealthConnectPermissions()
        } else {
            checkAndRequestBodySensorPermissions()
        }
    }
}

// ADVANCED EXAMPLE: With UI for user selection
// Add these views to your layout first, then use this code:

/*
private fun setupUIWithUserSelection() {
    // Setup user type spinner
    val userTypes = UserType.values().map { it.getDisplayName() }
    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypes)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    binding.spinnerUserType.adapter = adapter
    
    binding.btnRecordTemperature.setOnClickListener {
        val temperatureText = binding.etTemperature.text.toString()
        val userName = binding.etUserName.text.toString().trim()
        
        if (temperatureText.isEmpty()) {
            Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }

        try {
            val temperature = temperatureText.toDouble()
            
            // Create user info if name is provided
            val userInfo = if (userName.isNotEmpty()) {
                val selectedPosition = binding.spinnerUserType.selectedItemPosition
                val userType = UserType.values()[selectedPosition]
                
                UserInfo(
                    userName = userName,
                    userType = userType,
                    userId = null  // Optional: can be set if you have a user ID system
                )
            } else {
                // Default to SELF if no name provided
                UserInfo("Self", UserType.SELF)
            }
            
            viewModel.recordTemperature(temperature, userInfo)
            
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
        }
    }
}
*/

// EXAMPLE: Display user info in temperature history
// Update TemperatureHistoryActivity or adapter to show user info:

/*
fun bind(reading: TemperatureReading, bodyTemp: BodyTemperature?) {
    // ... existing code ...
    
    // Display user info if available
    bodyTemp?.userInfo?.let { userInfo ->
        binding.tvUserInfo.visibility = View.VISIBLE
        binding.tvUserInfo.text = "Recorded by: ${userInfo.userName} (${userInfo.userType.getDisplayName()})"
    } ?: run {
        binding.tvUserInfo.visibility = View.GONE
    }
}
*/
