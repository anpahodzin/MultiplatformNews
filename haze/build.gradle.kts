// Copyright 2023, Christopher Banes and the Haze project contributors
// SPDX-License-Identifier: Apache-2.0
plugins {
  alias(libs.plugins.multiplatform)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.androidLibrary)
}

android {
  namespace = "dev.chrisbanes.haze"
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  testOptions {
    unitTests.isIncludeAndroidResources = true
  }
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
}

kotlin {
  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  jvm()

  js { browser() }

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    commonMain {
      dependencies {
        api(compose.ui)
        implementation(compose.foundation)
      }
    }

    androidMain {
      dependencies {
        implementation(libs.androidx.collection)
        implementation(libs.androidx.core)
      }
    }

    val skikoMain by creating {
      dependsOn(commonMain.get())
    }

    val iosX64Main by getting{
      dependsOn(skikoMain)
    }
    val iosArm64Main by getting{
      dependsOn(skikoMain)
    }
    val iosSimulatorArm64Main by getting{
      dependsOn(skikoMain)
    }

    jvmMain {
      dependsOn(skikoMain)
    }

//    named("wasmJsMain") {
//      dependsOn(skikoMain)
//    }

    jsMain {
      dependsOn(skikoMain)
    }
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    freeCompilerArgs += "-Xcontext-receivers"
  }
}