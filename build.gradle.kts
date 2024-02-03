buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        val mokoResourceVersion = libs.versions.mokoResources.get()
        classpath("dev.icerock.moko:resources-generator:$mokoResourceVersion")
    }
}
plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.buildConfig).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
}
