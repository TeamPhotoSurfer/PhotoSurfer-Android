plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.readme.android.navigator"
}

dependencies {

    // Android Core
    implementation(AndroidXDependencies.coreKtx)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)
}
