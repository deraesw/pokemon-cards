
plugins {
    id("pcs.library.multiplatform")
}

kotlin {
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(projects.core.core)
            implementation(projects.core.database)
            implementation(projects.core.network)

            implementation(libs.koin.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {

        }
    }
}

android {
    namespace = "com.deraesw.pokemoncards.core.data"
}
