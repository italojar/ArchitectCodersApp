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
    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Javax inject
    implementation("javax.inject:javax.inject:1")
    // Arrow kt
    implementation("io.arrow-kt:arrow-core:1.1.5")
    //Test
    testImplementation("junit:junit:4.13.2")
}