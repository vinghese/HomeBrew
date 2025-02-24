plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("androidx.room")
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "edu.ddukk.homebrew"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.ddukk.homebrew"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    sourceSets {
        getByName("main") {
            res {
                srcDirs("src\\main\\res", "src\\main\\res\\layouts")
            }
        }
    }

//    packagingOptions { resources.excludes.add("**/*") }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

//    implementation(libs.mongodb.driver.sync)


    // KPS Compiler Options
    ksp(libs.androidx.room.compiler)

    //Kotlin Room Runtime
    implementation(libs.androidx.room.runtime)

    //Room Coroutine extension
    implementation(libs.androidx.room.ktx)

    //Android Lifecycle Extensions
    implementation(libs.androidx.lifecycle.extensions)
//    implementation(libs.androidx.lifecycle.compiler)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

