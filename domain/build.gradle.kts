plugins {
    id("pokedex.kotlin.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation("javax.inject:javax.inject:1")
}
