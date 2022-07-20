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

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)

    // Http Client Library
    implementation(ThirdPartyDependencies.retrofit)
    implementation(platform(ThirdPartyDependencies.okHttpBom))
    implementation(ThirdPartyDependencies.okHttp)
    implementation(ThirdPartyDependencies.okHttpLoggingInterceptor)

    // stfalconImageViewer
    implementation(ThirdPartyDependencies.stFalconImageViewer)

    // dotsIndocator
    implementation(ThirdPartyDependencies.dotsIndicator)
}
