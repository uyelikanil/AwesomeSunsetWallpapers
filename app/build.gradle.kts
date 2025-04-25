plugins {
    id("awesomesunsetwallpapers.android.application")
    id("awesomesunsetwallpapers.application.compose")
    id("awesomesunsetwallpapers.android.feature.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers"

    defaultConfig {
        applicationId = "com.anilyilmaz.awesomesunsetwallpapers"
        versionCode = 4
        versionName = "3.1.0"
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
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:main"))
    implementation(project(":feature:home"))
    implementation(project(":feature:wallpaperdetail"))


    implementation(libs.androidx.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
}