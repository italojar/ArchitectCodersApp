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

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    // Javax inject
    implementation("javax.inject:javax.inject:1")
    // Mockito
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    //Test
    testImplementation("junit:junit:4.13.2")
}