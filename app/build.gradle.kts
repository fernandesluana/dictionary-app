plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.luanafernandes.dictionaryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luanafernandes.dictionaryapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.hilt.work)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.material3)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.material)
    implementation(libs.converter.gson)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.assertk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.turbine)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.assertk)
    androidTestImplementation(libs.hilt.android.testing)
    debugImplementation(libs.androidx.compose.ui.tooling)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Okhttp, Retrofit and Kotlin Serialization + Converter for Retrofit
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.converter.moshi)

    // Gson
    implementation(libs.gson)

    // Dagger-Hilt (ksp support for hilt 2.51 is in alpha version for ksp 1.9.23-1.0.19)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)

    ksp(libs.androidx.hilt.compiler)
    kspAndroidTest(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.room)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
}