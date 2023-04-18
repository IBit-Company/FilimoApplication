plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = Config.nameSpace
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.lifecycleRuntime)

    //Testing
    testImplementation(Dependencies.AndroidX.junit)
    androidTestImplementation(Dependencies.AndroidX.extJunit)
    androidTestImplementation(Dependencies.AndroidX.espresso)
    androidTestImplementation(platform(Dependencies.AndroidX.composeBom))
    androidTestImplementation(Dependencies.AndroidX.composeUiTest)

    //Jetpack Compose
    implementation(Dependencies.AndroidX.composeActivity)
    implementation(platform(Dependencies.AndroidX.composeBom))
    implementation(Dependencies.AndroidX.composeUi)
    implementation(Dependencies.AndroidX.composeUiGraphics)
    implementation(Dependencies.AndroidX.composeUiPreview)
    implementation(Dependencies.AndroidX.material)
    debugImplementation(Dependencies.AndroidX.composeUiTooling)
    debugImplementation(Dependencies.AndroidX.composeUiTestManifest)

    //Ktor
    implementation(Dependencies.KtorClient.android)
    implementation(Dependencies.KtorClient.serialization)
    implementation(Dependencies.KtorClient.loggingJvm)

    //Kotlin Serialization
    implementation(Dependencies.KotlinSerialization.kotlinSerialization)

    //Dagger-Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)
}