plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.8.2")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
    compileOnly("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.21")
    compileOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.21-1.0.27")
    compileOnly("com.google.dagger:hilt-android-gradle-plugin:2.52")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.7")
}

gradlePlugin {
    plugins {
        register("kotlinLibrary") {
            id = "pokedex.kotlin.library"
            implementationClass = "PokedexKotlinLibraryPlugin"
        }
        register("androidLibrary") {
            id = "pokedex.android.library"
            implementationClass = "PokedexAndroidLibraryPlugin"
        }
        register("androidApplication") {
            id = "pokedex.android.application"
            implementationClass = "PokedexAndroidApplicationPlugin"
        }
        register("androidCompose") {
            id = "pokedex.android.compose"
            implementationClass = "PokedexAndroidComposePlugin"
        }
        register("androidHilt") {
            id = "pokedex.android.hilt"
            implementationClass = "PokedexAndroidHiltPlugin"
        }
    }
}
