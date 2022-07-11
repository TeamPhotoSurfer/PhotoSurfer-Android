plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {

    namespace = "com.photosurfer.android.shared"
}

dependencies {
    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.splashScreen)

    // Kotlin
    implementation(KotlinDependencies.kotlin)

    implementation(AndroidXDependencies.coreKtx)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)
}
