package helpers

import kotlinx.serialization.json.Json
import java.io.File

class Config(f: File) {
    private var content: ConfigStructure? = null

    init {
        val contents = f.readText()
        content = Json.decodeFromString<ConfigStructure>(contents)
    }

    //    val hertz = content?.hertz!!
    val stackSize = content?.stackSize!!
    val memorySize = content?.memorySize!!
}
