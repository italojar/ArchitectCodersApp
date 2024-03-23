import es.architectcoders.buildSrc.Libs

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
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
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
    testImplementation("io.mockk:mockk:1.13.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}