# Unit Tests for Health Connect Demo

## Quick Start

Run all tests:
```bash
./gradlew test
```

Run specific test class:
```bash
./gradlew test --tests "MainViewModelTest"
```

## Test Structure

```
test/
└── java/
    └── com/
        └── example/
            └── healthconnectdemo/
                ├── viewmodel/
                │   └── MainViewModelTest.kt (18 tests)
                ├── healthconnect/
                │   └── HealthConnectManagerTest.kt (16 tests)
                └── model/
                    └── TemperatureReadingTest.kt (20 tests)
```

## Test Summary

**Total: 54 Unit Tests**

### MainViewModelTest (18 tests)
- Temperature validation (5 tests)
- Temperature recording (7 tests)
- Permission checks (3 tests)
- History loading (3 tests)

### HealthConnectManagerTest (16 tests)
- Permissions (4 tests)
- Write operations (5 tests)
- Read operations (4 tests)
- Delete operations (3 tests)

### TemperatureReadingTest (20 tests)
- Temperature conversions (5 tests)
- Date/time formatting (4 tests)
- Record IDs (2 tests)
- Edge cases (4 tests)
- Data class operations (3 tests)
- Integration (2 tests)

## Key Features Tested

✅ Temperature validation (20°C - 45°C range)
✅ Temperature recording to Health Connect
✅ Temperature reading from Health Connect
✅ Temperature deletion
✅ Celsius/Fahrenheit conversions
✅ Date and time formatting
✅ Permission management
✅ Error handling
✅ Edge cases and boundaries

## Running Tests in Android Studio

1. **All tests:** Right-click `test` folder → Run 'Tests in test'
2. **Single class:** Click green arrow next to class name
3. **Single test:** Click green arrow next to test method

## View Test Results

After running tests, find results at:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

## Dependencies

All required test dependencies are already configured in `app/build.gradle`:
- JUnit 4.13.2
- Mockito 5.7.0
- Mockito-Kotlin 5.1.0
- Coroutines Test 1.7.3
- Architecture Components Testing 2.2.0
- Hilt Testing 2.50

For detailed documentation, see: [TEMPERATURE_TESTS.md](../../../TEMPERATURE_TESTS.md)
