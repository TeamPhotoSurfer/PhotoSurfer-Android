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
    namespace = "com.photosurfer.android.search"
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
    // TODO 채영 & 효림 tag 모듈로 합치기
    implementation(project(mapOf("path" to ":feature:register-tag")))
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)

    // Third Party Library
    implementation(ThirdPartyDependencies.flexBox)
}
