#!/bin/bash

# Health Connect Demo - Run All Tests Script
# This script runs tests for all product flavors

echo "========================================="
echo "Running Tests for All Flavors"
echo "========================================="
echo ""

# Function to run tests for a specific flavor
run_tests_for_flavor() {
    local flavor=$1
    local flavorCap="${flavor^}"
    
    echo "========================================="
    echo "Testing: ${flavorCap}Debug"
    echo "========================================="
    echo ""
    
    ./gradlew test${flavorCap}DebugUnitTest
    
    if [ $? -eq 0 ]; then
        echo "✓ ${flavorCap}Debug tests passed"
    else
        echo "✗ ${flavorCap}Debug tests failed"
    fi
    echo ""
}

# Clean previous results
echo "Cleaning previous test results..."
./gradlew clean
echo ""

# Run tests for all flavors
run_tests_for_flavor "dev"
run_tests_for_flavor "qa"
run_tests_for_flavor "prod"

echo "========================================="
echo "All Tests Completed"
echo "========================================="
echo ""
echo "Test reports are located in:"
echo "  app/build/reports/tests/"
echo ""
echo "Opening reports..."

# Open all test reports
find app/build/reports/tests -name "index.html" -type f -exec open {} \;

echo ""
echo "========================================="
echo "Test execution completed"
echo "========================================="
