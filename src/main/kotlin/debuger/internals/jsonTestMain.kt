package org.example.debuger.internals

import kotlinx.serialization.json.Json
import org.example.debuger.jsonInfo.DebugFile
import java.io.File



private val json = Json { prettyPrint = true }

fun main() {
    val data = json.decodeFromString<DebugFile>( File("src/main/resources/debug/debug.json").readText())
    val bug = DebugEngine(data)
}