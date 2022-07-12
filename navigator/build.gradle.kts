plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.photosurfer.android.navigator"
}

dependencies {
    implementation(project(":shared"))

    // Android Core
    implementation(AndroidXDependencies.coreKtx)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)
}
