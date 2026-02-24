# OnboardingApp

A modern Android application demonstrating Clean Architecture principles with Jetpack Compose, Kotlin, and DataStore for persistence.

## Features

- **Clean Architecture**: Separation of concerns with domain, data, and presentation layers
- **Jetpack Compose**: Modern declarative UI toolkit
- **Kotlin**: 100% Kotlin codebase
- **DataStore**: Type-safe data storage using Preferences DataStore
- **Compose Navigation**: Type-safe navigation with NavHost
- **Localization**: Support for English and Spanish languages
- **Hilt**: Dependency injection for Android
- **Material Design 3**: Modern Material Design components

## Project Structure

OnboardingApp/
- app/src/main/java/com/example/onboardingapp/
  - data/ (local data sources, repository implementations)
  - di/ (Hilt dependency injection modules)
  - domain/ (repository interfaces, use cases)
  - presentation/ (home, navigation, onboarding screens)
  - ui/theme/ (Compose theme)
  - MainActivity.kt
  - OnboardingApplication.kt
- app/src/main/res/
  - values/ (English strings)
  - values-es/ (Spanish strings)
  - xml/ (Backup rules)

## Architecture

### Domain Layer
- **Repository**: Interfaces defining data operations
- **UseCase**: Business logic operations

### Data Layer
- **Local**: DataStore implementation
- **Repository**: Repository implementations

### Presentation Layer
- **ViewModel**: UI state management
- **Screen**: Compose UI components
- **Navigation**: App navigation setup

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK 34
- Gradle 8.2

### Installation Steps

1. **Clone or download** this project to your local machine

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the OnboardingApp folder

3. **Sync Gradle**
   - Android Studio will automatically sync Gradle
   - Wait for the sync to complete

4. **Run the app**
   - Select an emulator or connected device
   - Click the Run button (green play icon)

## Dependencies

- AndroidX Core: 1.12.0
- Jetpack Compose: BOM 2023.10.01
- Material 3: Latest
- Navigation Compose: 2.7.5
- DataStore Preferences: 1.0.0
- Hilt: 2.48.1
- Kotlin: 1.9.20

## Building for Release

./gradlew assembleRelease

The APK will be generated at app/build/outputs/apk/release/

## Testing

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
