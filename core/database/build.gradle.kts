import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "database"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
//            implementation(compose.preview)
//            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
//            implementation(libs.ktor.client.okhttp)
            implementation(libs.sql.delight.android)
        }
        commonMain.dependencies {
            implementation(projects.core.core)
//            implementation(projects.core.core)
//            implementation(projects.core.network)

//            implementation(compose.runtime)
//            implementation(compose.foundation)
//            implementation(compose.material)
//            implementation(compose.material3)
//            implementation(compose.ui)
//            implementation(compose.components.resources)
//            implementation(compose.components.uiToolingPreview)
//            implementation(libs.androidx.lifecycle.viewmodel)
//            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.koin.core)
//            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.test)
//            implementation(libs.koin.compose)
//            implementation(libs.koin.compose.viewmodel.navigation)

//            implementation(libs.kotlinx.datetime)

//            implementation(libs.ktor.client.core)
//            implementation(libs.ktor.client.content.negotiation)
//            implementation(libs.ktor.serialization.kotlinx.json)
//            implementation(libs.ktor.client.logging)
//
//            implementation(libs.landscapist.coil3)

            implementation(libs.sql.delight.coroutines.extensions)
        }
        desktopMain.dependencies {
//            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            implementation(libs.sql.delight.sqlite)
        }
//        commonTest.dependencies {
//            implementation(libs.junit)
//            implementation(libs.kotlin.test)
//            implementation(libs.koin.test)
//            implementation(libs.kotlinx.coroutines.test)
//            implementation(libs.ktor.client.mock)
////            implementation(libs.sql.delight.sqlite)
//        }
        androidUnitTest.dependencies {
            implementation(libs.turbine)
            implementation(libs.sql.delight.sqlite)
        }
        jvmTest.dependencies {
            implementation(libs.turbine)
            implementation(libs.sql.delight.sqlite)
        }
    }
}

android {
    namespace = "com.deraesw.pokemoncards.core.database"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

sqldelight {
    databases {
        create("PokemonCardDatabase") {
            packageName = "com.deraesw.pokemoncards.core.database"
        }
    }
}