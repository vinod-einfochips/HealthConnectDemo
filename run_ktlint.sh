#!/bin/bash

# Health Connect Demo - Ktlint Runner Script
# This script runs ktlint checks and generates reports

echo "========================================="
echo "Health Connect Demo - Running Ktlint"
echo "========================================="
echo ""

# Clean previous reports
echo "Cleaning previous ktlint reports..."
rm -rf app/build/reports/ktlint

# Run ktlint check
echo ""
echo "Running ktlint check..."
./gradlew ktlintCheck

# Check if ktlint passed
if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "✓ Ktlint check passed!"
    echo "========================================="
else
    echo ""
    echo "========================================="
    echo "✗ Ktlint found issues. Check the report."
    echo "========================================="
fi

# Report paths
HTML_REPORT="app/build/reports/ktlint/ktlintMainSourceSetCheck/ktlintMainSourceSetCheck.html"

echo ""
echo "Ktlint reports generated at:"
echo "  app/build/reports/ktlint/"
echo ""

# Open HTML report in browser (macOS)
if [ -f "$HTML_REPORT" ]; then
    echo "Opening HTML report in browser..."
    open "$HTML_REPORT"
else
    echo "Searching for HTML reports..."
    find app/build/reports/ktlint -name "*.html" -exec open {} \; 2>/dev/null
fi

echo ""
echo "========================================="
echo "Ktlint execution completed"
echo "========================================="
