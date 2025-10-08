# Quick Test Start Guide

## Run Tests in 3 Steps

### Step 1: Run Tests

**For Dev flavor (default):**
```bash
./run_tests.sh
```

**For specific flavor:**
```bash
./run_tests.sh dev   # Development
./run_tests.sh qa    # QA
./run_tests.sh prod  # Production
```

**For all flavors:**
```bash
./run_all_tests.sh
```

### Step 2: View Results
The HTML report will automatically open in your browser at:
```
app/build/reports/tests/testDevDebugUnitTest/index.html
app/build/reports/tests/testQaDebugUnitTest/index.html
app/build/reports/tests/testProdDebugUnitTest/index.html
```

### Step 3: Check Results
- ✅ **Green** = All tests passed
- ❌ **Red** = Some tests failed (click for details)

## Alternative Methods

### Using Gradle
```bash
./gradlew testDebugUnitTest
```

### Using Android Studio
1. Right-click `MainViewModelTest.kt`
2. Click "Run 'MainViewModelTest'"
3. View results in Run panel

## Test Summary

### Total Tests: 50+

#### Positive Tests (20+)
- Valid temperature validation
- Successful recording
- Permission granting
- History loading

#### Negative Tests (20+)
- Invalid inputs
- Out of range values
- Permission denial
- Exception handling

#### Edge Cases (10+)
- Boundary values
- Decimal precision
- Special characters

## What's Tested?

### Temperature Validation
- ✅ Valid range: 20°C - 45°C
- ✅ Invalid inputs: empty, text, special chars
- ✅ Out of range: < 20°C or > 45°C
- ✅ Boundary values: exactly 20°C and 45°C

### Temperature Recording
- ✅ Successful recording
- ✅ Recording with user info
- ✅ Multiple recordings
- ✅ Error handling

### Permissions
- ✅ Permission granted
- ✅ Permission denied
- ✅ Exception handling

## Report Features

### HTML Report Shows:
- ⏱️ Execution time
- 📁 Test hierarchy
- 🔍 Failure details
- 📝 Stack traces

### Using Gradle
```bash
# Run tests for specific flavor
./gradlew testDevDebugUnitTest
./gradlew testQaDebugUnitTest
./gradlew testProdDebugUnitTest

# Run with detailed output
./gradlew testDevDebugUnitTest --info

# Run specific test class
./gradlew testDevDebugUnitTest --tests "MainViewModelTest"

# Run specific test method
./gradlew testDevDebugUnitTest --tests "MainViewModelTest.should validate temperature within valid range"
```
### Tests won't run?
```bash
./gradlew clean build
```bash
open app/build/reports/tests/testDebugUnitTest/index.html
```

### Need to sync?
```bash
./gradlew --refresh-dependencies
```

## Expected Results

All 50+ tests should pass:
```
Tests: 52
Passed: 52 ✓
Failed: 0
Success Rate: 100%
```

## Next Steps

1. ✅ Run tests
2. ✅ View HTML report
3. ✅ Check all tests pass
4. ✅ Review test coverage
5. ✅ Add more tests if needed

For detailed documentation, see: **JUNIT5_TEST_GUIDE.md**
