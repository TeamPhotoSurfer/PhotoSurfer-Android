import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.photosurfer.android.core"
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":navigator"))

    implementation(ThirdPartyDependencies.timber)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    coreLibraryDesugaring(AndroidXDependencies.desugarLibrary)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // ImageLoading Library
    implementation(ThirdPartyDependencies.glide)
}
