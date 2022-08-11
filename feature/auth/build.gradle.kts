import org.jetbrains.kotlin.konan.properties.Properties

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

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
    defaultConfig {
        buildConfigField(
            "String",
            "X_NAVER_CLIENT_ID",
            properties.getProperty("X_NAVER_CLIENT_ID")
        )
        buildConfigField(
            "String",
            "X_NAVER_CLIENT_SECRET",
            properties.getProperty("X_NAVER_CLIENT_SECRET")
        )
        buildConfigField(
            "String",
            "KAKAO_NATIVE_APP_KEY",
            properties.getProperty("KAKAO_NATIVE_APP_KEY")
        )
    }
    namespace = "com.photosurfer.android.auth"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(project(":navigator"))

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.splashScreen)

    // ThirdParty Library
    implementation(ThirdPartyDependencies.lottie)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // Naver - social Login
    implementation(ThirdPartyDependencies.naverAuth)

    // Kakao - social Login
    implementation(ThirdPartyDependencies.kakaoAuth)
}
