#!/bin/bash

# Health Connect Demo - Test Runner Script
# This script runs all unit tests and generates HTML reports

echo "========================================="
echo "Health Connect Demo - Running Unit Tests"
echo "========================================="
echo ""

# Default to dev flavor if not specified
FLAVOR=${1:-dev}
BUILD_TYPE="Debug"

echo "Running tests for: ${FLAVOR}${BUILD_TYPE}"
echo ""

# Clean previous test results
echo "Cleaning previous test results..."
./gradlew clean

# Run tests for the specified flavor
echo ""
echo "Running unit tests for ${FLAVOR}${BUILD_TYPE}..."
./gradlew test${FLAVOR^}${BUILD_TYPE}UnitTest --info

# Check if tests passed
if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "✓ All tests passed successfully!"
    echo "========================================="
else
    echo ""
    echo "========================================="
    echo "✗ Some tests failed. Check the report."
    echo "========================================="
fi

# Open HTML report
REPORT_PATH="app/build/reports/tests/test${FLAVOR^}${BUILD_TYPE}UnitTest/index.html"

echo ""
echo "Test report generated at:"
echo "$REPORT_PATH"
echo ""

# Open report in browser (macOS)
if [ -f "$REPORT_PATH" ]; then
    echo "Opening test report in browser..."
    open "$REPORT_PATH"
else
    echo "Report file not found. Searching for any test reports..."
    # Try to find and open any test report
    FOUND_REPORT=$(find app/build/reports/tests -name "index.html" -type f | head -n 1)
    if [ -n "$FOUND_REPORT" ]; then
        echo "Found report: $FOUND_REPORT"
        open "$FOUND_REPORT"
    else
        echo "No test reports found. Tests may have failed to run."
    fi
fi

echo ""
echo "========================================="
echo "Test execution completed"
echo "========================================="
echo ""
echo "Usage: ./run_tests.sh [flavor]"
echo "  Flavors: dev (default), qa, prod"
echo "  Example: ./run_tests.sh qa"
echo "========================================="
