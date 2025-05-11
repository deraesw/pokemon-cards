import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpModuleConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            println("wes - ${target.project}")
            println("wes - ${target.path}")
            println("wes - ${target.name}")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
                apply("io.gitlab.arturbosch.detekt")
            }

            configure<KotlinMultiplatformExtension> {
                androidTarget {
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
                        baseName = target.name
                        isStatic = true
                    }
                }

                jvm("desktop")

                with(sourceSets) {
                    commonTest.dependencies {
                        implementation(libs.findLibrary("junit").get())
                        implementation(libs.findLibrary("kotlin-test").get())

                    }
                }
            }

            configure<LibraryExtension> {
                compileSdk = libs.findVersion("android-compileSdk").get().toString().toInt()

                defaultConfig {
                    minSdk = libs.findVersion("android-minSdk").get().toString().toInt()
                }

                testOptions.targetSdk =
                    libs.findVersion("android-targetSdk").get().toString().toInt()

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}

//internal fun DependencyHandler.implementation(dependencyNotation: Any) {
//    this.add("implementation", dependencyNotation)
//}
