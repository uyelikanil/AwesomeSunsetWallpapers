import com.android.build.api.dsl.ApplicationExtension
import com.anilyilmaz.awesomesunsetwallpapers.configureAndroid
import com.anilyilmaz.awesomesunsetwallpapers.configureComposeForAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

class ApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.multiplatform")
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.compose")
            apply(plugin = "awesomesunsetwallpapers.multiplatform.library.koin")

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget()

                val iosTargets: List<KotlinNativeTarget> = listOf(
                    iosArm64(),
                    iosSimulatorArm64()
                )

                iosTargets.forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "composeApp"
                        isStatic = true

                        binaryOption(
                            "bundleId",
                            "com.anilyilmaz.awesomesunsetwallpapers.composeApp"
                        )

                        export(project(":core:system"))
                    }
                }
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(commonExtension = this)

                defaultConfig {
                    applicationId = "com.anilyilmaz.awesomesunsetwallpapers"
                    versionCode = 4
                    versionName = "3.1.0"
                    targetSdk = 36
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

                configureComposeForAndroid(commonExtension = this)
            }
        }
    }
}