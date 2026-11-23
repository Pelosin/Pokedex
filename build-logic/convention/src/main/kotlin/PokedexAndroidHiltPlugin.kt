import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class PokedexAndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            pluginManager.apply("com.google.dagger.hilt.android")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies.add("implementation", libs.findLibrary("hilt.android").get().get())
            dependencies.add("ksp", libs.findLibrary("hilt.android.compiler").get().get())
        }
    }
}
