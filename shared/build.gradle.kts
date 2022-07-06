plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {

    namespace = "com.photosurfer.android.shared"
}

dependencies {
    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)
}
