import com.android.build.gradle.LibraryExtension
import com.anilyilmaz.awesomesunsetwallpapers.configureAndroid
import com.anilyilmaz.awesomesunsetwallpapers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.apply

class MultiplatformFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "awesomesunsetwallpapers.multiplatform.library.core")
            apply(plugin = "awesomesunsetwallpapers.multiplatform.library.compose")
            apply(plugin = "awesomesunsetwallpapers.multiplatform.library.koin")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget()
                iosArm64()
                iosSimulatorArm64()

                sourceSets.apply {
                    val commonMain = getByName("commonMain")
                    commonMain.dependencies {
                        implementation(project(":core:common"))
                        implementation(project(":core:domain"))
                        implementation(project(":core:model"))
                        implementation(project(":core:ui"))
                        implementation(project(":core:network"))
                        implementation(project(":core:designsystem"))

                        implementation(libs.findLibrary("compose.runtime").get())
                        implementation(libs.findLibrary("compose.foundation").get())
                        implementation(libs.findLibrary("compose.material3").get())
                        implementation(libs.findLibrary("compose.material.icons.extended").get())
                        implementation(
                            libs.findLibrary("jetbrains.androidx.navigation.compose").get()
                        )

                        implementation(
                            libs.findLibrary("jetbrains.androidx.lifecycle.viewmodel").get()
                        )
                        implementation(
                            libs.findLibrary("jetbrains.androidx.lifecycle.runtime.compose").get()
                        )

                        implementation(libs.findLibrary("kotlinx.coroutines.core").get())
                        implementation(libs.findLibrary("kotlinx.serialization.json").get())

                        implementation(libs.findLibrary("coil.compose").get())
                        implementation(libs.findLibrary("coil.ktor").get())
                    }

                    val androidMain = findByName("androidMain")
                    androidMain?.dependencies {
                        implementation(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                        implementation(libs.findLibrary("kotlinx.coroutines.core").get())
                        implementation(libs.findLibrary("kotlinx.serialization.json").get())
                        implementation(libs.findLibrary("ktor.client.okhttp").get())
                    }
                    val iosMain = findByName("iosMain")
                    iosMain?.dependencies {
                        implementation(libs.findLibrary("ktor.client.darwin").get())
                    }
                }
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
            }
        }
    }
}