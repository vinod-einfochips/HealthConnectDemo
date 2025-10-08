#!/bin/bash

# Health Connect Demo - Build All Variants Script
# This script builds all product flavors and build types

echo "========================================="
echo "Building All Variants"
echo "========================================="
echo ""

# Function to build a specific variant
build_variant() {
    local variant=$1
    echo "Building $variant..."
    ./gradlew assemble${variant}
    
    if [ $? -eq 0 ]; then
        echo "✓ $variant built successfully"
    else
        echo "✗ $variant build failed"
        return 1
    fi
    echo ""
}

# Clean previous builds
echo "Cleaning previous builds..."
./gradlew clean
echo ""

# Build all variants
echo "========================================="
echo "Building Debug Variants"
echo "========================================="
echo ""

build_variant "DevDebug"
build_variant "QaDebug"
build_variant "ProdDebug"

echo "========================================="
echo "Building Release Variants"
echo "========================================="
echo ""

build_variant "DevRelease"
build_variant "QaRelease"
build_variant "ProdRelease"

# Show output location
echo "========================================="
echo "Build Complete!"
echo "========================================="
echo ""
echo "APKs are located in:"
echo "  app/build/outputs/apk/"
echo ""
echo "Variants built:"
echo "  - devDebug"
echo "  - devRelease"
echo "  - qaDebug"
echo "  - qaRelease"
echo "  - prodDebug"
echo "  - prodRelease"
echo ""

# List generated APKs
if [ -d "app/build/outputs/apk" ]; then
    echo "Generated APKs:"
    find app/build/outputs/apk -name "*.apk" -type f
fi

echo ""
echo "========================================="
echo "Build script completed"
echo "========================================="
