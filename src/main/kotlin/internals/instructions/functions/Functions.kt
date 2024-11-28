package internals.instructions.functions

import config
import java.io.File


open class Functions {
	val stdlibPath = run {
		config.paths[1].path
	}
	val functionsList: List<String> = File(stdlibPath).listFiles()!!.map { it.nameWithoutExtension }
}