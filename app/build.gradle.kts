import es.architectcoders.buildSrc.Libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    //secrets-gradle-plugin
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    //kotlin kpt plugin
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "es.architectcoders.spaceexplorer"
    compileSdk = 34

    defaultConfig {
        applicationId = "es.architectcoders.spaceexplorer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }

        buildFeatures {
            buildConfig = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":usecases")))
    implementation(project(mapOf("path" to ":data")))

    //Core
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.recyclerView)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.legacySupport)

    // Activity
    implementation(Libs.AndroidX.Activity.ktx)
    // Fragment
    implementation(Libs.AndroidX.Fragment.ktx)
    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    // Navigation
    implementation(Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.uiKtx)
    //splash
    implementation(Libs.AndroidX.SplashScreen.ktx)
    // Room
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    annotationProcessor(Libs.AndroidX.Room.compiler)
    kapt(Libs.AndroidX.Room.compiler)
    // Coroutines
    implementation(Libs.Kotlin.Coroutines.core)
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Hilt
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)
    // Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converterGson)
    // OkHttp
    implementation(Libs.OkHttp3.loginInterceptor)
    // Maps
    implementation(Libs.PlayServices.maps)
    // Arrow kt
    implementation(Libs.Arrow.core)
    //Glide
    implementation (Libs.Glide.glide)
    kapt (Libs.Glide.compiler)
    // Shimmer
    implementation(Libs.Shimmer.shimmer)
    //Test
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.turbine)
    testImplementation("io.mockk:mockk:1.13.2")

    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.contrib)
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.Hilt.test)
    androidTestImplementation(Libs.Kotlin.Coroutines.test)
    kaptAndroidTest(Libs.Hilt.compiler)

    androidTestImplementation(Libs.OkHttp3.mockWebServer)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}