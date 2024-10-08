package helpers

import kotlinx.serialization.json.Json
import java.io.File

class Config(f: File) {
    private var content: ConfigStructure? = null

    init {
        val contents = f.readText()
        content = Json.decodeFromString<ConfigStructure>(contents)
    }

    val hertz = content?.hertz!!.toLong() / 2
    val stackSize = content?.stackSize!!
    val memorySize = content?.memorySize!!
    val paths = content?.locations!!
}
