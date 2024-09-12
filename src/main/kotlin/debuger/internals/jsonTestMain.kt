package org.example.debuger.internals

import kotlinx.serialization.json.Json
import org.example.debuger.jsonInfo.DebugFile

val dataRaw = """
    {
      "breakPoints": [
        {
          "line": 1,
          "enabled": true
        },
        {
          "line": 2,
          "enabled": true
        }
      ],
      "lineSpecific": {
        "2": ["registers ALL"],
        "3": ["memoryRange 0 10"]
      },
      "eachIteration": [
        "registers"
      ]
    }
""".trimIndent()


fun main() {
    val data = Json.decodeFromString<DebugFile>(dataRaw)
    println(data)
}