plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
}

kotlin {

    val mokoMvvmVersion = "0.13.0"
    val ktor_version = "2.3.3"
    val coroutines_version = "1.7.2"
    val kotlinx_ser_version = "1.5.1"
    val koin_version = "3.4.0"

    // Only Android
    val coil_compose_version = "2.4.0"

    // Only ios
    val image_loader_version = "1.6.4"

    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export("dev.icerock.moko:mvvm-core:$mokoMvvmVersion")
            export("dev.icerock.moko:mvvm-flow:$mokoMvvmVersion")
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // View model
                api("dev.icerock.moko:mvvm-core:$mokoMvvmVersion")
                api("dev.icerock.moko:mvvm-flow:$mokoMvvmVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")


                // Serialization
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinx_ser_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_ser_version")

                // Koin
                implementation("io.insert-koin:koin-core:$koin_version")

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                // Specific ktor
                implementation("io.ktor:ktor-client-android:$ktor_version")
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")

                // Specific coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

                // Asunc mage loader only android
                implementation("io.coil-kt:coil-compose:$coil_compose_version")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                //Specific ktor
                implementation("io.ktor:ktor-client-darwin:$ktor_version")

                // Async image loader
                api("io.github.qdsfdhvh:image-loader:$image_loader_version")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "cm.pizzodev.cmphotoviewer.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
