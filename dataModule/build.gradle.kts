import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.sqlDelight)
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
        commonMain.dependencies {
            implementation(projects.domainModule)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.logging)
            implementation(libs.koin.core)
            implementation(libs.kermit)
            implementation(libs.multiplatformSettings)
            implementation(libs.sqlDelight.coroutines)
            implementation(libs.paging.common)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqlDelight.driver.android)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqlDelight.driver.sqlite)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
            implementation(libs.sqlDelight.driver.js)
            implementation(npm("@cashapp/sqldelight-sqljs-worker", libs.versions.sqlDelight.get()))
            implementation(npm("sql.js", "1.10.3"))
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqlDelight.driver.native)
        }
    }
}

android {
    namespace = "org.example.dataModule"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

@Suppress("INLINE_FROM_HIGHER_PLATFORM")
buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
    val keystoreFile = project.rootProject.file("local.properties")
    val properties = if (keystoreFile.exists()) {
        Properties().apply { load(keystoreFile.inputStream()) }
    } else Properties()

    buildConfigField("newsApiUrl", "https://newsapi.org/v2/")
    buildConfigField("newsApiKey", properties.getProperty("NEWS_API_KEY") ?: "")
}

sqldelight {
    databases {
        create("MyDatabase") {
            packageName.set("org.example.kmpnews")
            generateAsync.set(true)
        }
    }
}