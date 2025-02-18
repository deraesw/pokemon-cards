plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.sqlDelight) apply false
}

detekt {
    toolVersion = libs.versions.detekt.toString()
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}