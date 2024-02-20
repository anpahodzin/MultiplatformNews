import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.incremental.parsing.classesFqNames

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinx.serialization)
    id("dev.icerock.mobile.multiplatform-resources")
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

    js {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            export(projects.domainModule)
            export(projects.dataModule)

            export(libs.decompose)
            export(libs.essenty)
            export(libs.moko.resources)
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(projects.domainModule)
                implementation(projects.dataModule)
                implementation(projects.mokoResourcesCompose)

                implementation(compose.runtime)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api(libs.decompose)
                api(libs.essenty)
                implementation(libs.decompose.compose)
                implementation(libs.composeImageLoader)
                implementation(libs.kermit)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
                implementation(libs.koin.core)
                api(libs.moko.resources)
//                implementation(libs.moko.resources.compose)
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)
            implementation(libs.compose.uitooling)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.koin.android)
        }

        val jvmMain by getting {
//            dependsOn(commonMain)
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.compose.uiToolingPreview)
            }
        }

        val jsMain by getting {
//            dependsOn(commonMain)
            dependencies {
                implementation(compose.html.core)
                implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.1"))
                implementation(npm("sql.js", "1.8.0"))
                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            }
        }

//        val iosX64Main by getting {
//            resources.srcDirs("build/generated/moko/iosX64Main/src")
//        }
//        val iosArm64Main by getting {
//            resources.srcDirs("build/generated/moko/iosArm64Main/src")
//        }
//        val iosSimulatorArm64Main by getting {
//            resources.srcDirs("build/generated/moko/iosSimulatorArm64Main/src")
//        }
        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api(projects.domainModule)
                api(projects.dataModule)
            }
        }
    }
}

android {
    namespace = "org.example.kmpnews"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()

        applicationId = "org.example.kmpnews.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
        java.srcDirs("build/generated/moko/androidMain/src")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.kmpnews.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}

multiplatformResources {
    resourcesPackage.set("org.example.kmpnews")
    resourcesClassName.set("MR")
}