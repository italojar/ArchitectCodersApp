import es.architectcoders.buildSrc.Libs

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data")))

    // Coroutines
    implementation(Libs.Kotlin.Coroutines.core)
    // Javax inject
    implementation(Libs.JavaX.inject)
    //Test
    testImplementation(Libs.JUnit.junit)
}