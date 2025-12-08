import com.anilyilmaz.awesomesunsetwallpapers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformLibraryKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    val commonMain = getByName("commonMain")
                    commonMain.dependencies {
                        // no BOM in common — use versioned artifact via version catalog
                        implementation(libs.findLibrary("koin-core").get())
                        implementation(libs.findLibrary("koin-compose-viewModel").get())
                    }
                    // If the module has Android target, Android-only Koin bits go here:
                    val androidMain = findByName("androidMain")
                    androidMain?.dependencies {
                        implementation(libs.findLibrary("koin-android").get())
                        implementation(libs.findLibrary("koin-compose").get())
                    }
                }
            }
        }
    }
}
