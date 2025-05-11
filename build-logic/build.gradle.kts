plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("KmpModuleConvention") {
            id = "pcs.library.multiplatform"
            implementationClass = "KmpModuleConvention"
        }
    }
}
