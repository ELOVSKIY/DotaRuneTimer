plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.helicoptera.dotarunetimer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.helicoptera.dotarunetimer"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    // General compose dependencies

    // Compose for Wear OS Dependencies
    implementation("androidx.wear.compose:compose-material:1.3.1")

    // Foundation is additive, so you can use the mobile version in your Wear OS app.
    implementation("androidx.wear.compose:compose-foundation:1.3.1")
    implementation("androidx.wear.compose:compose-navigation:1.3.1")

    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("com.google.android.gms:play-services-wearable:18.2.0")

    // Tiles
    implementation("androidx.wear.tiles:tiles:1.3.0")
    implementation("androidx.wear.tiles:tiles-material:1.3.0")

    // ???
    implementation("com.google.android.horologist:horologist-compose-tools:0.4.8")
    implementation("com.google.android.horologist:horologist-tiles:0.4.8")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.activity:activity-compose")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")

    // Constraint-Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.compose.material3:material3-android:1.2.1") // to use assisted inject
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Wear
    implementation("androidx.wear.watchface:watchface-complications-data-source-ktx:1.2.1")
    implementation("androidx.wear:wear-tooling-preview:1.0.0")
    implementation("androidx.wear.tiles:tiles:1.4.0-alpha04")

//    // Tile
//    implementation("com.google.android.horologist:horologist-compose-tools:0.4.8")
//    implementation("com.google.android.horologist:horologist-tiles:0.4.8")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}