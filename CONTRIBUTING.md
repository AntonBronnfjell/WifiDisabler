# Contributing to WiFi Disabler for Android

Thank you for your interest in contributing to WiFi Disabler! This document provides guidelines for contributing to the project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Environment](#development-environment)
- [Development Workflow](#development-workflow)
- [Coding Standards](#coding-standards)
- [Commit Message Convention](#commit-message-convention)
- [Pull Request Process](#pull-request-process)
- [Testing Guidelines](#testing-guidelines)

## Code of Conduct

### Our Standards

- Be respectful and constructive
- Focus on what is best for the community and end users
- Accept constructive criticism gracefully
- Report inappropriate behavior
- Help create a positive environment

## Getting Started

### Prerequisites

- **Android Studio**: 4.0 or higher
- **JDK**: Java Development Kit 8 or higher
- **Android SDK**: API 16-30
- **Git**: For version control
- **Physical Device**: Recommended for Ethernet testing (emulator may not support Ethernet)

### Initial Setup

```bash
# Fork the repository on GitHub

# Clone your fork
git clone https://github.com/YOUR_USERNAME/project-android-app-java_wifi-disabler.git
cd project-android-app-java_wifi-disabler

# Add upstream remote
git remote add upstream https://github.com/ORIGINAL/project-android-app-java_wifi-disabler.git

# Create develop branch if not exists
git checkout -b develop origin/develop

# Open project in Android Studio
# File -> Open -> Select project directory

# Sync Gradle files
# Tools -> Android -> Sync Project with Gradle Files
```

## Development Environment

### Android Studio Configuration

1. **SDK Manager**: Ensure SDKs for API 16-30 are installed
2. **AVD Manager**: Create emulators for testing different API levels
3. **Kotlin Plugin**: Install for potential future Kotlin migration
4. **Android Lint**: Enable for code quality checks

### Recommended Plugins

- **ADB Idea**: Advanced ADB commands
- **Android WiFi ADB**: Wireless debugging
- **Key Promoter X**: Learn keyboard shortcuts
- **SonarLint**: Code quality analysis

## Development Workflow

We follow Gitflow workflow:

### Branch Strategy

- **main**: Production-ready code
- **develop**: Integration branch for features
- **feature/\***: New features
- **fix/\***: Bug fixes
- **hotfix/\***: Critical production fixes
- **release/\***: Release preparation

### Creating a Feature

```bash
# Update develop branch
git checkout develop
git pull upstream develop

# Create feature branch
git checkout -b feature/your-feature-name

# Make your changes
# ...

# Commit changes (see commit guidelines)
git add .
git commit -m "feat: add new feature description"

# Push to your fork
git push origin feature/your-feature-name

# Create Pull Request on GitHub
```

### Working on a Bug Fix

```bash
# Create fix branch from develop
git checkout develop
git checkout -b fix/bug-description

# Fix the bug
# ...

# Commit with fix type
git commit -m "fix: resolve bug description"

# Push and create PR
git push origin fix/bug-description
```

## Coding Standards

### Java Style Guide

Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html):

#### Naming Conventions

```java
// Classes: PascalCase
public class WiFiManagerHelper { }

// Methods: camelCase
public void disableWiFi() { }

// Variables: camelCase
private String connectionStatus;

// Constants: UPPER_SNAKE_CASE
public static final String DEFAULT_TIMEOUT = "30000";

// Package names: lowercase
package com.example.wifidisabler.utils;
```

#### Code Organization

```java
public class ExampleClass {
    // 1. Constants
    private static final String TAG = "ExampleClass";
    
    // 2. Static variables
    private static int instanceCount = 0;
    
    // 3. Instance variables
    private String mName;
    private int mAge;
    
    // 4. Constructors
    public ExampleClass() { }
    
    // 5. Lifecycle methods (for Activities/Fragments)
    @Override
    protected void onCreate(Bundle savedInstanceState) { }
    
    // 6. Public methods
    public void publicMethod() { }
    
    // 7. Private methods
    private void privateMethod() { }
    
    // 8. Inner classes
    private class InnerClass { }
}
```

### XML Layout Guidelines

```xml
<!-- Use meaningful IDs -->
<Button
    android:id="@+id/btnDisableWifi"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/disable_wifi" />

<!-- Group related attributes -->
<!-- 1. ID -->
<!-- 2. Layout width/height -->
<!-- 3. Layout constraints/positioning -->
<!-- 4. Padding/margin -->
<!-- 5. Style/appearance -->
<!-- 6. Text/content -->
<!-- 7. Event handlers -->
```

### Resource Naming

```
// Layouts
activity_main.xml
fragment_settings.xml
dialog_confirmation.xml
item_network_list.xml

// Drawables
ic_wifi_on_24dp.xml
bg_rounded_button.xml
shape_card_border.xml

// Values
colors.xml
strings.xml
dimens.xml
styles.xml

// IDs
btn_* (buttons)
tv_* (text views)
et_* (edit texts)
iv_* (image views)
rv_* (recycler views)
```

### Documentation

#### JavaDoc Comments

```java
/**
 * Manages WiFi state changes based on Ethernet connectivity.
 * 
 * This class monitors network connection changes and automatically
 * disables WiFi when an Ethernet connection is detected.
 * 
 * @author Your Name
 * @version 1.0.2
 * @since 1.0.0
 */
public class WiFiManager {
    
    /**
     * Disables WiFi on the device.
     * 
     * Requires CHANGE_WIFI_STATE permission. This method will
     * check for permission before attempting to disable WiFi.
     * 
     * @param context Application context for accessing WiFi service
     * @return true if WiFi was successfully disabled, false otherwise
     * @throws SecurityException if required permission is not granted
     */
    public boolean disableWiFi(Context context) {
        // Implementation
    }
}
```

## Commit Message Convention

We use [Conventional Commits](https://www.conventionalcommits.org/):

### Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting, missing semicolons, etc.)
- **refactor**: Code refactoring
- **perf**: Performance improvements
- **test**: Adding or updating tests
- **chore**: Build process or auxiliary tool changes
- **ci**: CI configuration changes

### Scopes

- **service**: Background service changes
- **ui**: User interface changes
- **network**: Network detection logic
- **permissions**: Permission handling
- **receiver**: Broadcast receiver changes
- **gradle**: Build configuration

### Examples

```bash
feat(service): add automatic WiFi re-enable on Ethernet disconnect

Implements logic to automatically re-enable WiFi when Ethernet
connection is lost, providing seamless connectivity fallback.

Closes #123
```

```bash
fix(network): resolve Ethernet detection on Android 11

Fixed compatibility issue where Ethernet connections were not
detected on Android API 30 due to NetworkCapabilities changes.

Fixes #456
```

```bash
docs(readme): add troubleshooting section

Added common issues and solutions for users experiencing
problems with automatic WiFi management.
```

```bash
refactor(ui): migrate MainActivity to MVVM architecture

Separated UI logic from business logic by implementing
ViewModel pattern for better testability and maintainability.
```

## Pull Request Process

### Before Submitting

- [ ] Code follows project style guidelines
- [ ] All tests pass
- [ ] New code has test coverage
- [ ] JavaDoc comments added for public methods
- [ ] No compiler warnings
- [ ] Tested on multiple Android versions (if possible)
- [ ] Tested on physical device with Ethernet adapter
- [ ] Updated README.md if needed
- [ ] Updated CHANGELOG.md

### PR Description Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix (non-breaking change fixing an issue)
- [ ] New feature (non-breaking change adding functionality)
- [ ] Breaking change (fix or feature causing existing functionality to change)
- [ ] Documentation update

## Testing
Describe testing performed:
- [ ] Unit tests added/updated
- [ ] Instrumented tests added/updated
- [ ] Tested on Android API [version numbers]
- [ ] Tested with Ethernet adapter

## Screenshots (if applicable)
Add screenshots showing UI changes

## Checklist
- [ ] My code follows project style guidelines
- [ ] I have performed a self-review
- [ ] I have commented complex code
- [ ] I have updated documentation
- [ ] My changes generate no new warnings
- [ ] I have added tests proving my fix/feature works
- [ ] New and existing tests pass locally

## Related Issues
Closes #(issue number)
```

### Review Process

1. Automated checks run (lint, tests)
2. Code review by maintainer
3. Address feedback and requested changes
4. Final approval
5. Merge into develop branch

## Testing Guidelines

### Unit Tests

```java
@Test
public void testWiFiDisable_whenEthernetConnected_shouldDisableWiFi() {
    // Arrange
    Context context = ApplicationProvider.getApplicationContext();
    WiFiManager wifiManager = new WiFiManager(context);
    
    // Act
    boolean result = wifiManager.disableWiFi();
    
    // Assert
    assertTrue(result);
    assertFalse(wifiManager.isWiFiEnabled());
}
```

### Instrumented Tests

```java
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
        new ActivityScenarioRule<>(MainActivity.class);
    
    @Test
    public void testDisableButton_whenClicked_shouldDisableWiFi() {
        // Test UI interaction
        onView(withId(R.id.btnDisableWifi))
            .perform(click());
        
        onView(withId(R.id.tvStatus))
            .check(matches(withText("WiFi Disabled")));
    }
}
```

### Test Coverage

- Aim for 80%+ code coverage
- All public methods should have tests
- Test edge cases and error conditions
- Mock external dependencies (WiFi system services)

## Additional Guidelines

### Performance

- Avoid memory leaks (use WeakReferences where appropriate)
- Minimize battery consumption
- Efficient network monitoring
- Proper service lifecycle management

### Security

- Validate all permissions before use
- Secure sensitive data
- Handle exceptions gracefully
- Log errors appropriately (no sensitive information)

### Accessibility

- Add content descriptions for UI elements
- Support TalkBack
- Ensure adequate contrast ratios
- Test with accessibility services enabled

## Questions or Need Help?

- **Issues**: Open a GitHub issue
- **Discussions**: Use GitHub Discussions
- **Email**: Contact maintainers for private concerns

## Recognition

Contributors will be acknowledged in the project README and release notes.

Thank you for contributing to WiFi Disabler! 🚀

