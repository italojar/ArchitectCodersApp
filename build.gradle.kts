// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(es.architectcoders.buildSrc.Libs.androidGradlePlugin)
    }
}
plugins {
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    //hilt
    id("com.google.dagger.hilt.android") version "2.49" apply false
    //secrets-gradle-plugin
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    //navigation-safe-args
    id("androidx.navigation.safeargs") version "2.5.3" apply false
    //kotlin
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
}