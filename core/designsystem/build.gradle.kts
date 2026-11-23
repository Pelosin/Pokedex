plugins {
    id("pokedex.android.library")
    id("pokedex.android.compose")
}

android {
    namespace = "com.example.mypokedex.core.designsystem"
}

dependencies {
    //Accompanist - (gif painter)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
}
