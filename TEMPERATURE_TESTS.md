# Temperature Unit Tests Documentation

## Overview
This document describes the comprehensive unit test suite for temperature-related functionality in the Health Connect Demo app.

## Test Files Created

### 1. MainViewModelTest.kt
**Location:** `app/src/test/java/com/example/healthconnectdemo/viewmodel/MainViewModelTest.kt`

**Test Categories:**
- **Temperature Validation Tests** (5 tests)
  - Valid temperature in range (20°C - 45°C)
  - Temperature below minimum
  - Temperature above maximum
  - Invalid input handling
  - Edge case handling

- **Temperature Recording Tests** (7 tests)
  - Successful recording with valid temperature
  - Failure with temperature below minimum (< 20°C)
  - Failure with temperature above maximum (> 45°C)
  - Boundary temperature handling (20°C and 45°C)
  - Exception handling from HealthConnectManager
  - Normal body temperature range testing
  
- **Permission Check Tests** (3 tests)
  - Permission granted status
  - Permission denied status
  - Exception handling during permission check

- **Temperature History Tests** (3 tests)
  - Loading temperature history successfully
  - Handling empty results
  - Exception handling during history load

**Total Tests:** 18

### 2. HealthConnectManagerTest.kt
**Location:** `app/src/test/java/com/example/healthconnectdemo/healthconnect/HealthConnectManagerTest.kt`

**Test Categories:**
- **Permission Tests** (4 tests)
  - Correct permission set validation
  - All permissions granted scenario
  - No permissions granted scenario
  - Partial permissions granted scenario

- **Write Temperature Tests** (5 tests)
  - Creating record with correct temperature
  - Using current time when not specified
  - Correct zone offset setting
  - Recording method validation
  - Various temperature values handling

- **Read Temperature Tests** (4 tests)
  - Reading flow of temperature records
  - Handling empty results
  - Time range filter validation
  - Temperature value preservation

- **Delete Temperature Tests** (3 tests)
  - Deletion with correct record ID
  - Exception handling on deletion failure
  - Multiple deletions handling

**Total Tests:** 16

### 3. TemperatureReadingTest.kt
**Location:** `app/src/test/java/com/example/healthconnectdemo/model/TemperatureReadingTest.kt`

**Test Categories:**
- **Temperature Conversion Tests** (5 tests)
  - Celsius formatting
  - Celsius rounding to one decimal
  - Fahrenheit formatting
  - Fahrenheit rounding to one decimal
  - Celsius to Fahrenheit conversion accuracy
  - Normal body temperature range validation

- **Date and Time Formatting Tests** (4 tests)
  - DateTime formatting
  - Date storage validation
  - Timestamp storage validation
  - Zone offset storage validation

- **Record ID Tests** (2 tests)
  - Record ID storage
  - Unique record IDs validation

- **Edge Cases Tests** (4 tests)
  - Minimum valid temperature (20°C)
  - Maximum valid temperature (45°C)
  - High precision temperature handling
  - Zero temperature handling

- **Data Class Tests** (3 tests)
  - Copy functionality
  - Equality validation
  - toString representation

- **Integration Tests** (2 tests)
  - Complete workflow testing
  - Collection storage testing

**Total Tests:** 20

## Test Dependencies Added

The following dependencies were added to `app/build.gradle`:

```gradle
testImplementation 'junit:junit:4.13.2'
testImplementation 'org.mockito:mockito-core:5.7.0'
testImplementation 'org.mockito.kotlin:mockito-kotlin:5.1.0'
testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3'
testImplementation 'androidx.arch.core:core-testing:2.2.0'
testImplementation 'com.google.dagger:hilt-android-testing:2.50'
kaptTest 'com.google.dagger:hilt-compiler:2.50'
```

## Running the Tests

### From Android Studio:
1. Right-click on the `test` directory
2. Select "Run 'Tests in 'test''"

### Run Specific Test Class:
1. Open the test file
2. Click the green play button next to the class name
3. Or right-click the class name and select "Run"

### Run Single Test:
1. Click the green play button next to the test method
2. Or right-click the test method and select "Run"

### From Command Line:
```bash
# Run all unit tests
./gradlew test

# Run tests for specific variant
./gradlew testDebugUnitTest

# Run specific test class
./gradlew test --tests "com.example.healthconnectdemo.viewmodel.MainViewModelTest"

# Run with coverage
./gradlew testDebugUnitTest jacocoTestReport
```

## Test Coverage Summary

**Total Unit Tests:** 54

### Coverage by Component:
- **MainViewModel:** 18 tests
  - Temperature validation
  - Temperature recording
  - Permission management
  - History loading

- **HealthConnectManager:** 16 tests
  - Permission handling
  - Write operations
  - Read operations
  - Delete operations

- **TemperatureReading:** 20 tests
  - Temperature formatting
  - Unit conversions
  - Date/time handling
  - Data class operations

## Key Test Scenarios Covered

### 1. Temperature Validation
- ✅ Valid range: 20°C - 45°C
- ✅ Boundary values: exactly 20°C and 45°C
- ✅ Invalid values: below 20°C and above 45°C
- ✅ Invalid input: empty strings, non-numeric values

### 2. Temperature Recording
- ✅ Successful recording with valid data
- ✅ Proper error messages for invalid temperatures
- ✅ Exception handling from Health Connect
- ✅ Metadata (recording method, zone offset)

### 3. Temperature Reading
- ✅ Reading multiple records
- ✅ Empty result handling
- ✅ Time range filtering
- ✅ Data preservation

### 4. Temperature Formatting
- ✅ Celsius formatting (e.g., "37.5°C")
- ✅ Fahrenheit formatting (e.g., "99.5°F")
- ✅ Date/time formatting
- ✅ Rounding to one decimal place

### 5. Data Integrity
- ✅ Record ID uniqueness
- ✅ Timestamp accuracy
- ✅ Zone offset handling
- ✅ Temperature unit conversions

## Best Practices Implemented

1. **Mocking:** Using Mockito to mock dependencies
2. **Coroutines:** Using `runTest` for coroutine testing
3. **LiveData:** Using `InstantTaskExecutorRule` for LiveData testing
4. **Isolation:** Each test is independent and isolated
5. **Descriptive Names:** Test names clearly describe what they test
6. **Arrange-Act-Assert:** Following AAA pattern
7. **Edge Cases:** Testing boundary conditions and error scenarios

## Troubleshooting

### If tests fail to run:
1. Sync Gradle files
2. Clean and rebuild project: `./gradlew clean build`
3. Invalidate caches and restart Android Studio

### Common Issues:
- **Missing dependencies:** Run `./gradlew build` to download dependencies
- **Compilation errors:** Ensure all imports are correct
- **Mock issues:** Verify Mockito version compatibility

## Future Enhancements

Potential areas for additional testing:
- UI tests using Espresso
- Integration tests with actual Health Connect
- Performance tests for large datasets
- Stress tests for concurrent operations
- End-to-end workflow tests

## Notes

- All tests use mocked dependencies for isolation
- Tests are designed to run quickly without external dependencies
- Temperature range validation follows medical standards
- Tests cover both success and failure scenarios
- Exception handling is thoroughly tested
