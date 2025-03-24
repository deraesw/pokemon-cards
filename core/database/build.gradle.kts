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
            implementation(libs.koin.android)
            implementation(libs.sql.delight.android)
        }
        commonMain.dependencies {
            implementation(projects.core.core)
            implementation(libs.koin.core)
            implementation(libs.koin.test)
            implementation(libs.sql.delight.coroutines.extensions)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sql.delight.sqlite)
        }
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
