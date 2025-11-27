import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.anilyilmaz.awesomesunsetwallpapers.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("application") {
            id = "awesomesunsetwallpapers.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("multiplatformLibraryCompose") {
            id = "awesomesunsetwallpapers.multiplatform.library.compose"
            implementationClass = "MultiplatformLibraryComposeConventionPlugin"
        }
        register("multipaltformFeature") {
            id = "awesomesunsetwallpapers.multiplatform.feature"
            implementationClass = "MultiplatformFeatureConventionPlugin"
        }
        register("androidFeatureKoin") {
            id = "awesomesunsetwallpapers.android.feature.koin"
            implementationClass = "AndroidFeatureKoinConventionPlugin"
        }
        register("multipaltformLibraryKoin") {
            id = "awesomesunsetwallpapers.multiplatform.library.koin"
            implementationClass = "MultiplatformLibraryKoinConventionPlugin"
        }
        register("multipaltformLibraryCore") {
            id = "awesomesunsetwallpapers.multiplatform.library.core"
            implementationClass = "MultiplatformLibraryCoreConventionPlugin"
        }
    }
}