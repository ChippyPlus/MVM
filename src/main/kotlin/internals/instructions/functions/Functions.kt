package internals.instructions.functions

import config
import java.io.File

open class Functions {
	val stdlibPath = run {
		config?.paths?.get(1)?.path ?: "${System.getProperty("user.dir")}/src/main/resources/lib"
	}
	val functionsList: List<String> = File(stdlibPath).listFiles()!!.map { it -> it.nameWithoutExtension }
}