This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

// TODO
Resources
https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html
https://dev.pokemontcg.io/
https://github.com/terrakok/kmp-awesome?tab=readme-ov-file
https://m3.material.io/
https://creatie.ai

Libs

https://github.com/sqldelight/sqldelight
https://sqldelight.github.io/sqldelight/2.0.2/multiplatform_sqlite/
https://insert-koin.io/
https://github.com/Kotlin/kotlinx-datetime
https://ktor.io/docs/client-create-new-application.html

Images
attacks icons
https://bulbapedia.bulbagarden.net/wiki/

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…