plugins {
    kotlin("kapt")
    id("awesomesunsetwallpapers.android.application")
    id("awesomesunsetwallpapers.android.hilt")
    id("awesomesunsetwallpapers.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers"

    defaultConfig {
        applicationId = "com.anilyilmaz.awesomesunsetwallpapers"
        versionCode = 2
        versionName = "2.0.0"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:main"))
    implementation(project(":feature:home"))
    implementation(project(":feature:wallpaperdetail"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
}