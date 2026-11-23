plugins {
    id("pokedex.android.library")
    id("pokedex.android.compose")
    id("pokedex.android.hilt")
}

android {
    namespace = "com.example.mypokedex.ui"
}

dependencies {
    api(project(":domain"))
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    //Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil3.coil.gif)
    implementation(libs.accompanist.drawablepainter)
}
