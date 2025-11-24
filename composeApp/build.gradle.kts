plugins {
    kotlin("multiplatform")
    id("awesomesunsetwallpapers.multiplatform.feature")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
    id("awesomesunsetwallpapers.multiplatform.library.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:resource"))
            implementation(compose.components.resources)
            implementation(project(":core:common"))
            implementation(project(":core:network"))
            implementation(project(":core:data"))
            implementation(project(":core:domain"))
            implementation(project(":core:designsystem"))
            implementation(project(":core:model"))
            implementation(project(":feature:main"))
            implementation(project(":feature:home"))
            implementation(project(":feature:wallpaperdetail"))
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "composeApp"
            isStatic = true

            binaryOption(
                "bundleId",
                "com.anilyilmaz.awesomesunsetwallpapers.composeApp"
            )
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.composeApp"
}
