import com.android.build.api.dsl.LibraryExtension
import com.anilyilmaz.awesomesunsetwallpapers.configureComposeForAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class MultiplatformLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.compose")

            val extension = extensions.getByType<LibraryExtension>()
            configureComposeForAndroid(extension)
        }
    }
}