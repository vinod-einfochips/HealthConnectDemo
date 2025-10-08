# ktlint Fix Guide

## Quick Fix

If you encounter ktlint errors in GitHub Actions or locally, run:

```bash
./gradlew ktlintFormat
```

This will automatically fix most code style issues.

## Common ktlint Issues and Fixes

### 1. Class body should not start with blank line

**Issue:**
```kotlin
class MyClass {

    fun myFunction() { }
}
```

**Fix:**
```kotlin
class MyClass {
    fun myFunction() { }
}
```

### 2. Missing trailing comma

**Issue:**
```kotlin
val list = listOf(
    "item1",
    "item2"
)
```

**Fix:**
```kotlin
val list = listOf(
    "item1",
    "item2",
)
```

### 3. Multiline expression should start on a new line

**Issue:**
```kotlin
fun myFunction() = runTest {
    // code
}
```

**Fix:**
```kotlin
fun myFunction() =
    runTest {
        // code
    }
```

### 4. Newline expected before expression body

**Issue:**
```kotlin
fun myFunction(): String = "value"
```

**Fix (for multiline):**
```kotlin
fun myFunction(): String =
    "value"
```

## Commands

### Check for issues
```bash
./gradlew ktlintCheck
```

### Auto-fix issues
```bash
./gradlew ktlintFormat
```

### Check specific source set
```bash
./gradlew ktlintMainSourceSetCheck
./gradlew ktlintTestSourceSetCheck
```

### Format specific source set
```bash
./gradlew ktlintMainSourceSetFormat
./gradlew ktlintTestSourceSetFormat
```

## Pre-commit Hook

To automatically format code before committing, add to `.git/hooks/pre-commit`:

```bash
#!/bin/sh
./gradlew ktlintFormat --daemon
git add -u
```

Make it executable:
```bash
chmod +x .git/hooks/pre-commit
```

## IDE Integration

### Android Studio / IntelliJ IDEA

1. Install ktlint plugin from marketplace
2. Or use built-in formatter with ktlint rules:
   - Settings → Editor → Code Style → Kotlin
   - Set from → Predefined style → Kotlin style guide

### VS Code

Install the "Kotlin Language" extension which includes ktlint support.

## GitHub Actions

The workflow now automatically runs:
1. `ktlintFormat` - Auto-fixes issues
2. `ktlintCheck` - Verifies code style

If ktlint still fails after auto-fix, the build will fail and you'll need to manually fix remaining issues.

## Disable Rules (Not Recommended)

If you need to disable specific rules, add to `build.gradle`:

```gradle
ktlint {
    filter {
        exclude("**/generated/**")
    }
    disabledRules.set(setOf("no-wildcard-imports"))
}
```

## Best Practices

1. ✅ Run `./gradlew ktlintFormat` before committing
2. ✅ Set up pre-commit hook for automatic formatting
3. ✅ Configure IDE to use Kotlin style guide
4. ✅ Review ktlint reports in CI/CD
5. ❌ Don't disable rules without good reason

## Troubleshooting

### ktlint fails in CI but passes locally

**Cause:** Different ktlint versions or configurations

**Solution:**
```bash
# Clean and rebuild
./gradlew clean
./gradlew ktlintFormat
./gradlew ktlintCheck
```

### Format doesn't fix all issues

**Cause:** Some issues require manual fixing

**Solution:** Check the error message and fix manually

### Conflicts with IDE formatter

**Cause:** IDE using different code style

**Solution:** Configure IDE to use Kotlin style guide

---

**Last Updated:** 2025-10-08
**ktlint Version:** Check `build.gradle` for version
