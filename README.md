# WiFi Disabler for Android

An Android application that automatically disables WiFi when an Ethernet connection is detected. Designed to solve connectivity issues on older Android devices (<8.X) and certain modern devices that fail to properly switch from WiFi to Ethernet.

## Overview

Many older Android devices struggle to automatically switch from WiFi to Ethernet when an Ethernet adapter is plugged in. This creates connectivity issues in industrial and commercial settings where Ethernet is the preferred connection method. WiFi Disabler monitors network changes and automatically disables WiFi when Ethernet becomes available, ensuring seamless connectivity.

## Problem Statement

**Target Devices**: Android versions <8.X and some newer devices  
**Issue**: Device stays connected to WiFi even when Ethernet adapter is plugged in  
**Impact**: Navori devices, chart screens, and production line displays lose connectivity  
**Solution**: Automatic WiFi management based on Ethernet detection

## Features

### Core Functionality
- **Automatic WiFi Management**: Disables WiFi when Ethernet connection is detected
- **Connection Monitoring**: Real-time monitoring of network connection changes
- **Background Service**: Runs as a persistent service to monitor connections continuously
- **System Integration**: Deep integration with Android's connectivity APIs

### User Experience
- **Material Design UI**: Modern, intuitive interface following Material Design guidelines
- **Status Indicators**: Visual feedback showing current connection state
- **Manual Override**: Option to manually control WiFi when needed
- **Notification Support**: Alerts user of connection state changes

### Additional Features
- **Auto-Start on Boot**: Service starts automatically when device boots
- **Auto-Update**: Integrated app updater for seamless updates
- **Lightweight**: Minimal resource consumption
- **Permission Management**: Requests only necessary permissions

## Screenshots

> *Add screenshots showing:*
> - Main screen with connection status
> - Settings screen
> - Notification display

## Tech Stack

### Android Platform
- **Min SDK**: API 16 (Android 4.1 Jelly Bean)
- **Target SDK**: API 30 (Android 11)
- **Compile SDK**: API 30

### Programming Language
- **Language**: Java 8
- **Build System**: Gradle

### Key Dependencies
- **AndroidX AppCompat**: Backward compatibility support
- **Material Components**: Material Design UI components
- **ConstraintLayout**: Flexible layout system
- **CardView**: Material card design
- **AppUpdater**: Automatic update functionality (javiersantos/AppUpdater)

### Testing
- **JUnit 4**: Unit testing framework
- **Espresso**: UI testing framework
- **AndroidX Test**: Testing utilities

## Architecture

### Components

#### Activities
- **MainActivity**: Primary user interface and control center
- Configuration screens for user preferences

#### Services
- **WiFiMonitorService**: Background service monitoring network changes
- Runs continuously to detect Ethernet connections
- Manages WiFi state based on connection type

#### Broadcast Receivers
- **NetworkChangeReceiver**: Listens for connectivity changes
- **BootReceiver**: Starts service on device boot

#### Utilities
- Connection state detection
- WiFi management helpers
- Permission handlers

### Design Patterns
- **Service-Oriented**: Background service for continuous monitoring
- **Observer Pattern**: Broadcast receivers for system events
- **Singleton**: Service management
- **MVVM**: Separation of UI and business logic

## Installation

### Prerequisites
- Android device running API 16+ (Android 4.1+)
- USB debugging enabled (for development)
- Android Studio 4.0+ (for building from source)

### From Source

```bash
# Clone the repository
git clone <repository-url>
cd project-android-app-java_wifi-disabler

# Open in Android Studio
# Build -> Make Project

# Run on connected device or emulator
# Run -> Run 'app'
```

### Building APK

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# APK location:
# app/build/outputs/apk/debug/app-debug.apk
# app/build/outputs/apk/release/app-release.apk
```

### Installation via ADB

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Usage

### First Launch
1. Open WiFi Disabler app
2. Grant required permissions:
   - Network state access
   - WiFi state modification
   - Background service
3. Enable the service
4. App begins monitoring connections

### Normal Operation
1. Connect Ethernet adapter to device
2. App automatically detects Ethernet connection
3. WiFi is disabled automatically
4. Notification confirms the change
5. When Ethernet is disconnected, WiFi can be re-enabled

### Manual Control
- Use app interface to manually toggle WiFi
- Override automatic behavior when needed
- View current connection status

## Permissions Required

### Network Permissions
- `ACCESS_NETWORK_STATE`: Monitor network connectivity
- `ACCESS_WIFI_STATE`: Check WiFi status
- `CHANGE_WIFI_STATE`: Enable/disable WiFi

### System Permissions
- `RECEIVE_BOOT_COMPLETED`: Start service on boot
- `FOREGROUND_SERVICE`: Run persistent background service
- `WAKE_LOCK`: Keep service active

## Configuration

### App Settings
- Enable/disable automatic WiFi management
- Configure boot start behavior
- Set notification preferences
- Adjust connection check intervals

### Build Configuration

Edit `app/build.gradle`:

```gradle
android {
    defaultConfig {
        applicationId "com.example.wifidisabler"
        minSdkVersion 16
        targetSdkVersion 30
        versionCode 3
        versionName "1.0.2b"
    }
}
```

## Use Cases

### Industrial Applications
- **Production Line Displays**: Navori digital signage
- **Chart Screens**: Real-time data displays
- **Manufacturing Equipment**: Android-based control panels

### Commercial Settings
- **POS Systems**: Point-of-sale terminals
- **Kiosks**: Information and transaction kiosks
- **Digital Signage**: Display systems in stores/offices

### Technical Scenarios
- **Older Device Support**: Android <8.X compatibility
- **Ethernet Priority**: Ensure wired connection preference
- **Network Reliability**: Eliminate WiFi interference

## Troubleshooting

### WiFi Not Disabling
- Verify permissions granted
- Check service is running
- Ensure Ethernet adapter is supported
- Restart app and service

### Service Not Starting
- Enable auto-start permission
- Check battery optimization settings
- Verify boot receiver is registered
- Review logs for errors

### Ethernet Not Detected
- Confirm Ethernet adapter compatibility
- Check physical connection
- Verify network settings
- Test adapter with other apps

### App Crashes
- Check Android version compatibility
- Review logcat for stack traces
- Verify all permissions granted
- Clear app cache and data

## Development

### Project Structure

```
project-android-app-java_wifi-disabler/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/wifidisabler/
│   │   │   │   ├── activities/
│   │   │   │   ├── services/
│   │   │   │   ├── receivers/
│   │   │   │   └── utils/
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

### Adding Features

1. Create feature branch from develop
2. Implement feature following Android best practices
3. Write unit tests
4. Update documentation
5. Submit pull request

### Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate test coverage report
./gradlew jacocoTestReport
```

## Contributing

Contributions are welcome! Please see `CONTRIBUTING.md` for guidelines.

### Development Guidelines
- Follow Android coding standards
- Write JavaDoc comments
- Include unit tests for new features
- Update documentation
- Test on multiple Android versions

## Versioning

- **Current Version**: 1.0.2b
- **Version Code**: 3

We use [Semantic Versioning](https://semver.org/):
- MAJOR.MINOR.PATCH format
- MAJOR: Breaking changes
- MINOR: New features (backward compatible)
- PATCH: Bug fixes

## License

MIT License - See LICENSE file for details.

## Acknowledgments

### Supported By
Manufacturing company for funding development to fix connectivity issues with Navori devices and production line displays.

### Special Thanks
- Android community for connectivity solutions
- AppUpdater library by @javiersantos
- Material Design team at Google

## Related Projects

- [Android Ethernet Manager](https://github.com/example/ethernet-manager)
- [Network Priority Manager](https://github.com/example/network-priority)

## Support

### Issues
Report bugs and feature requests via GitHub Issues.

### Contact
For business inquiries and support contracts, contact the development team.

## Roadmap

### Planned Features
- [ ] Network priority configuration
- [ ] Connection statistics and logging
- [ ] Multiple network profile support
- [ ] Advanced Ethernet detection algorithms
- [ ] Widget for quick status view
- [ ] Dark mode support
- [ ] Localization (multiple languages)

### Future Enhancements
- VPN connection handling
- Mobile data management
- Connection speed testing
- Network diagnostics tools

## Changelog

### Version 1.0.2b (Current)
- Bug fixes for connection detection
- Improved service stability
- Updated dependencies

### Version 1.0.1
- Added auto-update feature
- Enhanced UI with Material Design
- Performance optimizations

### Version 1.0.0
- Initial release
- Basic WiFi management
- Ethernet detection
- Background service

---

**Built with ❤️ for reliable industrial Android connectivity**
