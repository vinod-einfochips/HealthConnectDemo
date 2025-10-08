# Ktlint Quick Start

## Run Ktlint in 3 Steps

### Step 1: Run Ktlint Check
```bash
./run_ktlint.sh
```

### Step 2: View HTML Report
The report will automatically open in your browser

### Step 3: Fix Issues (if any)
```bash
./gradlew ktlintFormat
```

## Quick Commands

### Check Code Style
```bash
./gradlew ktlintCheck
```

### Auto-Fix Issues
```bash
./gradlew ktlintFormat
```

### Run with Script
```bash
./run_ktlint.sh
```

## What Ktlint Checks

- Import ordering
- Line length (max 120 characters)
- Trailing whitespace
- Indentation (4 spaces)
- Unused imports
- Final newline
- Code formatting
- Naming conventions

## Report Formats

- HTML Report (Visual, browser-friendly)
- Checkstyle XML (CI/CD integration)
- JSON (Structured data)
- Plain Text (Console output)

## Common Workflow

```bash
# 1. Check for issues
./run_ktlint.sh

# 2. Auto-fix what can be fixed
./gradlew ktlintFormat

# 3. Check again
./gradlew ktlintCheck
```

## Integration

### Before Commit
```bash
./gradlew ktlintCheck && git commit -m "Your message"
```

### In CI/CD
```yaml
- name: Run ktlint
  run: ./gradlew ktlintCheck
```

For detailed documentation, see: KTLINT_GUIDE.md
