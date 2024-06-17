# Multiplatform News
=
Generated using https://terrakok.github.io/Compose-Multiplatform-Wizard/

<img src = "screen/demo.png" width="350" alt="Multiplatform News"/>

Compose KMP Demo Application
-
Support platform: Android,IOS, Desktop(JVM), Web(JS)

<img src = "screen/ios-demo.gif" width="350" alt="Multiplatform News ios"/>

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Browser
Run the browser application: `./gradlew :composeApp:jsBrowserDevelopmentRun`\
If you need to update Yarn: `./gradlew :kotlinUpgradeYarnLock`

