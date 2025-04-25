import com.anilyilmaz.awesomesunsetwallpapers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                "implementation"(platform(libs.findLibrary("koin-bom").get()))
                "implementation"(libs.findLibrary("koin-core").get())
            }
        }
    }
}
