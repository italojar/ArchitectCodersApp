import es.architectcoders.buildSrc.Libs

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))

    // Coroutines
    implementation(Libs.Kotlin.Coroutines.core)
    // Javax inject
    implementation(Libs.JavaX.inject)
    // Arrow kt
    implementation(Libs.Arrow.core)
    //Test
    testImplementation(Libs.JUnit.junit)
}