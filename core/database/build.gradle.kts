plugins {
    id("pcs.library.multiplatform")
    alias(libs.plugins.sqlDelight)
}

kotlin {
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
}

sqldelight {
    databases {
        create("PokemonCardDatabase") {
            packageName = "com.deraesw.pokemoncards.core.database"
        }
    }
}
