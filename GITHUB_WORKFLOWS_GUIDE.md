# GitHub Workflows Guide

## Overview

This project includes automated CI/CD workflows for building, testing, and releasing the Health Connect Demo app.

## Workflows

### 1. Build Dev APK (`build-dev.yml`)

**Triggers:**
- Push to `develop` branch
- Pull request to `develop` branch
- Manual trigger via GitHub Actions UI

**What it does:**
- Builds Dev Debug APK
- Builds Dev Release APK
- Uploads both APKs as artifacts
- Shows build information in summary

**Artifacts:**
- `dev-debug-apk` - Debug build for development
- `dev-release-apk` - Release build for development testing

**Retention:** 30 days

---

### 2. Build QA APK (`build-qa.yml`)

**Triggers:**
- Push to `qa` branch
- Pull request to `qa` branch
- Manual trigger via GitHub Actions UI

**What it does:**
- Builds QA Debug APK
- Builds QA Release APK
- Uploads both APKs as artifacts
- Shows build information in summary

**Artifacts:**
- `qa-debug-apk` - Debug build for QA testing
- `qa-release-apk` - Release build for QA environment

**Retention:** 30 days

---

### 3. Build All Flavors (`build-all-flavors.yml`)

**Triggers:**
- Push to `main` branch
- Pull request to `main` branch
- Manual trigger via GitHub Actions UI

**What it does:**
- Builds all flavors (dev, qa, prod) in both debug and release
- Uses matrix strategy for parallel builds
- Uploads all 6 APK variants as separate artifacts
- Creates build summary

**Artifacts:**
- `dev-debug-apk`
- `dev-release-apk`
- `qa-debug-apk`
- `qa-release-apk`
- `prod-debug-apk`
- `prod-release-apk`

**Retention:** 30 days

---

### 4. Test and Build (`test-and-build.yml`)

**Triggers:**
- Push to any branch
- Pull request to any branch
- Manual trigger via GitHub Actions UI

**What it does:**
1. **Test Job:**
   - Runs unit tests
   - Uploads test results
   - Publishes test report

2. **Lint Job:**
   - Runs Android Lint
   - Uploads lint results

3. **ktlint Job:**
   - Runs ktlint code style checks
   - Uploads ktlint results

4. **Build Job:**
   - Determines flavor based on branch
   - Builds appropriate APK
   - Uploads APK artifact

**Branch to Flavor Mapping:**
- `develop` → Dev flavor
- `qa` → QA flavor
- `main` → Prod flavor
- Other branches → Dev flavor (default)

**Artifacts:**
- `test-results` - Unit test reports
- `lint-results` - Lint analysis
- `ktlint-results` - Code style check results
- `{flavor}-debug-apk` - Built APK

**Retention:** 30 days

---

### 5. Release Build (`release.yml`)

**Triggers:**
- Push tag matching `v*` (e.g., `v1.0.0`)
- Manual trigger with version input

**What it does:**
- Builds release APKs for all flavors
- Creates GitHub release
- Uploads APKs to release
- Generates release notes

**Release Assets:**
- `health-connect-demo-dev-{version}.apk`
- `health-connect-demo-qa-{version}.apk`
- `health-connect-demo-prod-{version}.apk`

---

## Usage

### Triggering Workflows Manually

1. Go to **Actions** tab in GitHub
2. Select the workflow you want to run
3. Click **Run workflow**
4. Select branch and click **Run workflow**

### Downloading Build Artifacts

1. Go to **Actions** tab
2. Click on the workflow run
3. Scroll to **Artifacts** section
4. Click on artifact name to download

### Creating a Release

```bash
# Tag the commit
git tag v1.0.0

# Push the tag
git push origin v1.0.0
```

The release workflow will automatically:
- Build all release APKs
- Create GitHub release
- Upload APKs to release

---

## Workflow Configuration

### Environment Variables

All workflows use:
- **JDK Version:** 17
- **Distribution:** Temurin
- **Gradle Cache:** Enabled
- **Android SDK:** Provided by GitHub Actions

### Build Commands

**Dev Debug:**
```bash
./gradlew assembleDevDebug
```

**Dev Release:**
```bash
./gradlew assembleDevRelease
```

**QA Debug:**
```bash
./gradlew assembleQaDebug
```

**QA Release:**
```bash
./gradlew assembleQaRelease
```

**Prod Debug:**
```bash
./gradlew assembleProdDebug
```

**Prod Release:**
```bash
./gradlew assembleProdRelease
```

---

## Branch Strategy

### Recommended Git Flow

```
main (prod)
    ↑
    └── qa (qa)
            ↑
            └── develop (dev)
                    ↑
                    └── feature/* (dev)
```

### Workflow Triggers by Branch

| Branch | Triggered Workflows |
|--------|-------------------|
| `develop` | Build Dev, Test and Build |
| `qa` | Build QA, Test and Build |
| `main` | Build All Flavors, Test and Build |
| `feature/*` | Test and Build |
| `v*` tags | Release |

---

## Customization

### Adding Secrets

For signing APKs, add these secrets in GitHub:

1. Go to **Settings** → **Secrets and variables** → **Actions**
2. Add secrets:
   - `KEYSTORE_FILE` - Base64 encoded keystore
   - `KEYSTORE_PASSWORD` - Keystore password
   - `KEY_ALIAS` - Key alias
   - `KEY_PASSWORD` - Key password

### Modifying Workflows

Edit workflow files in `.github/workflows/`:

```yaml
# Example: Change JDK version
- name: Set up JDK 17
  uses: actions/setup-java@v4
  with:
    java-version: '17'  # Change this
    distribution: 'temurin'
```

### Adding New Flavors

If you add a new flavor (e.g., `staging`):

1. Update `build-all-flavors.yml`:
```yaml
strategy:
  matrix:
    flavor: [dev, qa, staging, prod]  # Add staging
```

2. Create dedicated workflow if needed

---

## Troubleshooting

### Build Fails

**Check:**
1. Gradle version compatibility
2. Dependencies are up to date
3. `local.properties` is correctly generated
4. Android SDK is available

**View logs:**
- Click on failed workflow
- Click on failed job
- Expand failed step

### Tests Fail

**Solutions:**
1. Run tests locally first: `./gradlew test`
2. Check test reports in artifacts
3. Fix failing tests before pushing

### Artifacts Not Found

**Reasons:**
- Build failed before artifact upload
- Artifact expired (30 day retention)
- Wrong artifact name

**Solution:**
- Check workflow logs
- Verify artifact path in workflow file

---

## Best Practices

### 1. Branch Protection

Enable branch protection for:
- `main`
- `qa`
- `develop`

**Settings:**
- Require pull request reviews
- Require status checks to pass
- Require branches to be up to date

### 2. Status Checks

Make these workflows required:
- Test and Build
- Lint
- ktlint

### 3. Artifact Management

- Download important artifacts before 30-day expiration
- Archive release APKs separately
- Use GitHub Releases for production builds

### 4. Versioning

Use semantic versioning for releases:
- `v1.0.0` - Major release
- `v1.1.0` - Minor release
- `v1.1.1` - Patch release

### 5. Commit Messages

Use conventional commits for better changelog:
```
feat: Add temperature history feature
fix: Fix permission dialog not showing
docs: Update README
ci: Add QA build workflow
```

---

## Workflow Status Badges

Add to README.md:

```markdown
![Build Dev](https://github.com/username/repo/actions/workflows/build-dev.yml/badge.svg)
![Build QA](https://github.com/username/repo/actions/workflows/build-qa.yml/badge.svg)
![Test and Build](https://github.com/username/repo/actions/workflows/test-and-build.yml/badge.svg)
```

---

## Cost Optimization

GitHub Actions free tier:
- **Public repos:** Unlimited minutes
- **Private repos:** 2,000 minutes/month

**Tips to save minutes:**
1. Use caching (already enabled)
2. Run tests only on relevant changes
3. Use `continue-on-error` for non-critical jobs
4. Limit concurrent workflows

---

## Security

### Secrets Management

**Never commit:**
- API keys
- Keystore files
- Passwords
- Tokens

**Use GitHub Secrets for:**
- Signing credentials
- API endpoints
- Service account keys

### Permissions

Workflows have minimal permissions by default. Add only required permissions:

```yaml
permissions:
  contents: read
  actions: read
  checks: write
```

---

## Support

For issues with workflows:
1. Check workflow logs
2. Review this guide
3. Check GitHub Actions documentation
4. Open an issue in the repository

---

**Last Updated:** 2025-10-08
**Workflows Version:** 1.0
**Maintained by:** Development Team
