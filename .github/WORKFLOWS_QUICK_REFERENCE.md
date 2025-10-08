# GitHub Workflows Quick Reference

## Available Workflows

| Workflow | File | Trigger | Purpose |
|----------|------|---------|---------|
| Build Dev | `build-dev.yml` | Push/PR to `develop` | Build Dev APKs |
| Build QA | `build-qa.yml` | Push/PR to `qa` | Build QA APKs |
| Build All | `build-all-flavors.yml` | Push/PR to `main` | Build all flavors |
| Test & Build | `test-and-build.yml` | Push/PR to any branch | Run tests and build |
| Release | `release.yml` | Tag `v*` | Create release |

## Quick Commands

### Trigger Build Manually
```bash
# Via GitHub UI: Actions → Select workflow → Run workflow
```

### Create Release
```bash
git tag v1.0.0
git push origin v1.0.0
```

### Download Artifacts
```bash
# Via GitHub UI: Actions → Workflow run → Artifacts section
```

## Branch → Flavor Mapping

| Branch | Flavor | Workflow |
|--------|--------|----------|
| `develop` | Dev | build-dev.yml |
| `qa` | QA | build-qa.yml |
| `main` | All | build-all-flavors.yml |
| `feature/*` | Dev | test-and-build.yml |

## Build Outputs

### Dev Build
- `app-dev-debug.apk`
- `app-dev-release.apk`

### QA Build
- `app-qa-debug.apk`
- `app-qa-release.apk`

### Prod Build
- `app-prod-debug.apk`
- `app-prod-release.apk`

## Status Checks

✅ **Required:**
- Unit Tests
- Lint
- ktlint
- Build

⚠️ **Optional:**
- Code coverage
- Security scan

## Artifact Retention

- **Duration:** 30 days
- **Download:** Via Actions tab
- **Cleanup:** Automatic after retention period

## Common Issues

### Build Failed
```bash
# Check logs in Actions tab
# Run locally: ./gradlew assembleDevDebug
```

### Tests Failed
```bash
# Run locally: ./gradlew testDevDebugUnitTest
# Check test reports in artifacts
```

### Workflow Not Triggered
- Check branch name matches trigger
- Verify workflow file syntax
- Check repository permissions

## Quick Links

- [Workflows Guide](../GITHUB_WORKFLOWS_GUIDE.md)
- [GitHub Actions Docs](https://docs.github.com/en/actions)
- [Gradle Build Docs](../BUILD_INSTRUCTIONS.md)

---

**Need Help?** Check the full [Workflows Guide](../GITHUB_WORKFLOWS_GUIDE.md)
