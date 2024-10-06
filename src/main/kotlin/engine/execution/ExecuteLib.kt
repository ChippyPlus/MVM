package engine.execution

import helpers.LibMetaData
import java.io.File

class ExecuteLib(val name: String) {
	private val f = File("./libs/$name.lib").readText()

	private fun getMeta(): LibMetaData {
		val data = f.split('\n')[0]
		val name = data.split(':')[0].removePrefix("!")
		val version = data.split(':')[1].removePrefix("!")
		val args = data.substring(name.length + version.length).split(':')
		return LibMetaData(name = name, version = version.toInt(), args = args)
	}

}