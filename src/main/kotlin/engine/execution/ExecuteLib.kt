package engine.execution

import java.io.File

class ExecuteLib(val name: String) {
	private val f = File("./libs/$name.lib").readText()

	private fun getMeta() {
		val data = f.split('\n')[0]
		val name = data.split(' ')[0].removePrefix("!")
		val args = data.substring(name.length).split(' ')
		return
	}

}