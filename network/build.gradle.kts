@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
}

android {
    namespace = "no.app.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.com.google.android.material.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Networking - Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp3.logging)

    // Serialization - Moshi
    implementation(libs.moshi.kotlin)
    kspTest(libs.moshi.codegen)
    implementation(libs.moshi.adapters)

    // Dependency Injection - Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)
}
