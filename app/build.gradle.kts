plugins {
    id("pokedex.android.application")
    id("pokedex.android.compose")
    id("pokedex.android.hilt")
}

android {
    namespace = "com.example.mypokedex"

    defaultConfig {
        applicationId = "com.example.mypokedex"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    // App shell: wires the layers together
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature:pokedex"))
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)

    //SplashScreen
    implementation(libs.androidx.core.splashscreen)
}
