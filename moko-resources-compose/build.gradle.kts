plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
}

android {
    namespace = "dev.icerock.moko.resources.compose"
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

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    js {
        browser()
        binaries.executable()
    }

    macosX64()
    macosArm64()

    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.resources)
                api(compose.runtime)
                api(compose.foundation)
            }
        }
    }
}
