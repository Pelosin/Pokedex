import com.android.build.gradle.LibraryExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class PokedexAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.android")
            pluginManager.apply("com.android.library")
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.configure<LibraryExtension> {
                compileSdk = 35
                defaultConfig {
                    minSdk = 35
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
            }
            tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions.jvmTarget = "11"
            }
            extensions.configure<DetektExtension> {
                buildUponDefaultConfig = true
                baseline = file("$rootDir/config/detekt/baseline-${project.name}.xml")
            }
            dependencies.add(
                "detektPlugins",
                "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7"
            )
        }
    }
}
