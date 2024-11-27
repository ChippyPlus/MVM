package helpers

import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.io.File

class Config(private val f: File) {
	private var content: ConfigStructure? = null

	init {
		runBlocking { init() }
	}

	val hertz = content?.hertz!!.toLong() / 2
	val stackSize = content?.stackSize ?: 12
	val memorySize = content?.memorySize ?: 1024
	val paths = content?.locations!!

	@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
	private suspend fun init() = CoroutineScope(Dispatchers.IO).launch(newSingleThreadContext("Kotlin's Config Init")) {
		val contents = f.readText()
		content = Json.decodeFromString<ConfigStructure>(contents)
	}.join()
}
