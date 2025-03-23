package com.deraesw.pokemoncards.core.network.mock

object MockCardSet {
    val json = """{
          "data": [
            {
              "id": "base1",
              "name": "Base",
              "series": "Base",
              "printedTotal": 102,
              "total": 102,
              "legalities": {
                "unlimited": "Legal"
              },
              "ptcgoCode": "BS",
              "releaseDate": "1999/01/09",
              "updatedAt": "2020/08/14 09:35:00",
              "images": {
                "symbol": "https://images.pokemontcg.io/base1/symbol.png",
                "logo": "https://images.pokemontcg.io/base1/logo.png"
              }
            }
          ],
          "page": 1,
          "pageSize": 250,
          "count": 123,
          "totalCount": 123
        }
    """.trimIndent()

    val failedJson = """{
          "unknown": [
            {
              "id": "base1",
              "name": "Base",
              "series": "Base",
              "printedTotal": 102,
              "total": 102,
              "legalities": {
                "unlimited": "Legal"
              },
              "ptcgoCode": "BS",
              "releaseDate": "1999/01/09",
              "updatedAt": "2020/08/14 09:35:00",
              "images": {
                "symbol": "https://images.pokemontcg.io/base1/symbol.png",
                "logo": "https://images.pokemontcg.io/base1/logo.png"
              }
            }
          ],
          "page": 1,
          "pageSize": 250,
          "count": 123,
          "totalCount": 123
        }
    """.trimIndent()
}
