This is a sample for a [Kotlin Multiplatform] project that uses the [Compose Multiplatform] UI framework.

<p float="center">
  <img width="400" alt="Screenshot" src="/readme_images/apps.png">
  <img width="300" alt="Screenshot" src="/readme_images/cm_multi.png">
</p>

<p float="left">
   <img width="400" alt="Screenshot" src="/readme_images/techstack.png">
   <img width="300" alt="Screenshot" src="/readme_images/tech_stack2.png">
</p>

## Dependencies list and usage

### Shared dependencies

* *dev.icerock.moko:mvvm-**: This is a Kotlin Multiplatform library that provides architecture components of Model-View-ViewModel for UI applications. 
* *io.ktor:ktor-client.**: Provides an end-to-end kotlin multiplatform client for connected applications. 
* *org.jetbrains.kotlinx:kotlinx-serialization-core*: Kotlin multiplatform JSON serialization *
* *io.insert-koin:koin-core*: Kotlin multiplatform DI manager

### Only Android:

* *io.coil-kt:coil-compose:* Compose image viewer

### Only iOS:

* *io.github.qdsfdhvh:image-loader:* Compose image viewer

## Project structure

The project includes three modules:

### shared

Common for both Android and iOS applications, code shared between the two platforms

This `shared` module is also where you’ll write your Compose Multiplatform code.
In `shared/src/commonMain/kotlin/App.kt`, there is the shared root `@Composable` function.

It uses Gradle as the build system. You can add dependencies and change settings in `shared/build.gradle.kts`.
The `shared` module builds into an Android library and an iOS framework.

### androidApp

This is a Kotlin module that builds into an Android application. It uses Gradle as the build system.
The `androidApp` module depends on and uses the `shared` module as a regular Android library.

### iosApp

This is an Xcode project that builds into an iOS application.
It depends on and uses the `shared` module as a CocoaPods dependency.

## Modules

androidApp   
│
└─── MainActivity.kt + AndroidManifest.xml + Gradle config
│
iosApp
│
└─── iOSApp.swift + info.plist + Podfile
│
shared
│
└───  androidMain
      │
      └─────────────── specific Android HTTP client + main.android.kt
      │
      iosMain
      ioaArm
      iosx64
      │
      └─────────────── specific Darwin HTTP client + main.ios.kt
      │
      commonMain
      │
      └───────────────  api: api interfaces
                        │
                        koin: koin DI setup and modules
                        │
                        repo: repository intefaces
                        │
                        ui: @Composable MainScreen and ViewModel
                        │
                        utils: utilities
                        │
                        App.kt: Application @Composable start + expected functions





