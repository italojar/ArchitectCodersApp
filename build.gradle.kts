// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(es.architectcoders.buildSrc.Libs.androidGradlePlugin)
        classpath(es.architectcoders.buildSrc.Libs.Hilt.gradlePlugin)
        classpath(es.architectcoders.buildSrc.Libs.secretsGradlePlugin)
        classpath(es.architectcoders.buildSrc.Libs.AndroidX.Navigation.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}