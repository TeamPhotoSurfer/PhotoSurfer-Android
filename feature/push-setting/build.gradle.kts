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
    namespace = "com.photosurfer.android.push_setting"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(project(":navigator"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.splashScreen)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)
}
