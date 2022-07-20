import org.jetbrains.kotlin.konan.properties.Properties

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version Versions.kotlinVersion
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
    defaultConfig {
        buildConfigField(
            "String",
            "PHOTO_SURFER_SERVER_BASE_URL",
            properties.getProperty("PHOTO_SURFER_SERVER_BASE_URL")
        )
    }
    namespace = "com.photosurfer.android"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":navigator"))
    implementation(project(":shared"))
    implementation(project(":feature:register-tag"))
    implementation(project(":feature:search-result"))
    implementation(project(":feature:push-setting"))
    implementation(project(":feature:search"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:main"))
    implementation(project(":feature:alarm-list"))

    // Kotlin
    implementation(KotlinDependencies.kotlin)
    implementation(KotlinDependencies.kotlinxSerialization)
    implementation(KotlinDependencies.dateTime)

    // Android Core
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.legacy)
    implementation(AndroidXDependencies.coroutines)
    implementation(AndroidXDependencies.paging3)
    implementation(AndroidXDependencies.splashScreen)

    // Material Design
    implementation(MaterialDesignDependencies.materialDesign)

    // Dagger-Hilt
    implementation(AndroidXDependencies.hilt)
    kapt(KaptDependencies.hiltCompiler)

    // Jetpack Navigation Component
    implementation(AndroidXDependencies.navigationFragment)
    implementation(AndroidXDependencies.navigationUI)

    // Jetpack Security
    implementation(AndroidXDependencies.security)

    // Jetpack Fragment
    implementation(AndroidXDependencies.fragment)

    // Jetpack Lifecycle
    implementation(AndroidXDependencies.lifeCycleKtx)
    implementation(AndroidXDependencies.lifecycleJava8)

    // ImageLoading Library
    // Glide
    implementation(ThirdPartyDependencies.glide)

    // Http Client Library
    implementation(ThirdPartyDependencies.retrofit)
    implementation(platform(ThirdPartyDependencies.okHttpBom))
    implementation(ThirdPartyDependencies.okHttp)
    implementation(ThirdPartyDependencies.okHttpLoggingInterceptor)

    // JsonConverterLibrary
    implementation(ThirdPartyDependencies.gson)
    implementation(ThirdPartyDependencies.gsonConverter)

    // Logger - Timber
    implementation(ThirdPartyDependencies.timber)

    // Firebase
    implementation(platform(FirebaseDependency.firebaseBom))
    implementation(FirebaseDependency.analyticsKtx)
    implementation(FirebaseDependency.firebaeMessaging)

    // test
    implementation(AndroidXDependencies.junit)
    androidTestImplementation(TestDependencies.androidTest)

    // flexbox layout
    implementation(AndroidXDependencies.flexBox)
}
