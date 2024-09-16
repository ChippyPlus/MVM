package debugger

import kotlinx.serialization.json.Json
import debugger.encoding.DebugFile
import java.io.File



private val json = Json { prettyPrint = true }

fun main() {
    val data = json.decodeFromString<DebugFile>( File("src/main/resources/debug/debug.json").readText())
    val bug = DebugEngine(data)
}