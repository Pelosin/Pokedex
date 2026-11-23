import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class PokedexAndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            // buildFeatures.compose on whichever android extension exists
            if (pluginManager.hasPlugin("com.android.application")) {
                extensions.configure<ApplicationExtension> { buildFeatures { compose = true } }
            } else {
                extensions.configure<LibraryExtension> { buildFeatures { compose = true } }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            fun find(alias: String) = libs.findLibrary(alias).get().get()

            DependencyHandlerScope.of(dependencies).apply {
                add("implementation", platform(find("androidx.compose.bom")))
                add("implementation", find("androidx.ui"))
                add("implementation", find("androidx.ui.graphics"))
                add("implementation", find("androidx.ui.tooling.preview"))
                add("implementation", find("androidx.foundation"))
                add("debugImplementation", find("androidx.ui.tooling"))
            }
        }
    }
}
