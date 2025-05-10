package com.deraesw.pokemoncards.core.network.mock

object MockType {
    val listJson = """
        {
          "data": [
            "Colorless",
            "Darkness"
          ]
        }
    """.trimIndent()

    val failedJson = """
        {
          "failed": [
            "Colorless",
            "Darkness"
          ]
        }
    """.trimIndent()
}
