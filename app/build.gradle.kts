plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.anilyilmaz.awesomesunsetwallpapers"
        minSdk = 21
        targetSdk = 34
        versionCode = 2
        versionName = "2.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("full") {
            buildConfigField("String", "BASE_URL", "\"https://api.pexels.com\"")
            buildConfigField("String", "API_KEY",
                "\"AYcBbQE9tWR1DQDCy4Lh3XobkrOi9XNM1J4v3x4RFspWNuGOZq6Aqh9u\""
            )
        }
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.appcompat)
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.runner)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    testImplementation(libs.androidx.paging.test)
    implementation(libs.retrofit.converter.moshi)
    testImplementation(libs.junit4)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}