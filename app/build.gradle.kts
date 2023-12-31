@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
}

android {
    namespace = "no.app.invenire"
    compileSdk = 34

    defaultConfig {
        applicationId = "no.app.invenire"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Modules
    implementation(project(mapOf("path" to ":network")))

    // Core and Extensions
    implementation(libs.core.ktx)

    // Lifecycle
    implementation(libs.lifecycle.runtime.ktx)

    // Compose UI and related libraries
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material)

    // Compose platform (BoM)
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    testImplementation(libs.turbine)
    testImplementation(libs.couroutines.test)

    // Debugging and tooling
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Dependency Injection - Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)

    // Serialization - Moshi
    implementation(libs.moshi.kotlin)
    kspTest(libs.moshi.codegen)
    implementation(libs.moshi.adapters)

    // Networking - Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp3.logging)

    // Image Loading - Coil
    implementation(libs.coil)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
}
