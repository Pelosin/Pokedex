plugins {
    id("pokedex.android.library")
    id("pokedex.android.hilt")
}

android {
    namespace = "com.example.mypokedex.data"
}

dependencies {
    api(project(":domain"))

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)

    //Preferences
    implementation(libs.androidx.preference.ktx)
}
