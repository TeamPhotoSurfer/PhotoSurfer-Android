plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.photosurfer.android.navigator"
}


dependencies {
    implementation(project(":domain"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)
}
