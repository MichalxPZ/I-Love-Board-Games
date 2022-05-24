plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.6.21"
}


android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/ASM")
    }

    hilt {
        enableExperimentalClasspathAggregation = true
    }
}

dependencies {
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)

    implementation(pl.org.akai.buildsrc.ProtoDataStore.protoDataStore)
    implementation(pl.org.akai.buildsrc.ProtoDataStore.preferenceDataStore)
    implementation(pl.org.akai.buildsrc.Serialization.serialization)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)


    implementation(project(Modules.core))
    implementation(project(Modules.coreUI))

    implementation(project(Modules.onboardingDomain))
    implementation(project(Modules.onboardingPresentation))

    implementation(project(Modules.profileScreenData))
    implementation(project(Modules.profileScreenDomain))
    implementation(project(Modules.profileScreenPresentation))

    implementation(project(Modules.gameListData))
    implementation(project(Modules.gameListDomain))
    implementation(project(Modules.gameListPresentation))

    implementation(project(Modules.rankingHistoryData))
    implementation(project(Modules.rankingHistoryDomain))
    implementation(project(Modules.rankingHistoryPresentation))

    implementation(project(Modules.synchronizationData))
    implementation(project(Modules.synchronizationDomain))
    implementation(project(Modules.synchronizationPresentation))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(pl.org.akai.buildsrc.Coil.coilCompose)

    implementation(pl.org.akai.buildsrc.Retrofit.okHttp)
    implementation(pl.org.akai.buildsrc.Retrofit.retrofit)
    implementation(pl.org.akai.buildsrc.Retrofit.okHttpLoggingInterceptor)
    implementation(pl.org.akai.buildsrc.Retrofit.moshiConverter)

    kapt(pl.org.akai.buildsrc.Room.roomCompiler)
    implementation(pl.org.akai.buildsrc.Room.roomKtx)
    implementation(pl.org.akai.buildsrc.Room.roomRuntime)


    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
}
