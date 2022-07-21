plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.photosurfer.android.shared"
}

dependencies {
    implementation(project(":domain"))

    // Android Core
    implementation(AndroidXDependencies.splashScreen)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)

    // dotsIndocator
    implementation(ThirdPartyDependencies.dotsIndicator)

    // Third Party Library
    implementation(ThirdPartyDependencies.flexBox)
}
