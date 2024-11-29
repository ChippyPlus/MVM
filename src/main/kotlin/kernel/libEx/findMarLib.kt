package kernel.libEx

import kernel.ExecuteLib
import java.io.File

fun ExecuteLib.findMarLib(name: String): String? {
	if (File("${vm.functions.stdlibPath}/$name.lib").exists()) {
		val path = File("${vm.functions.stdlibPath}/$name.lib").absolutePath
		return path
	}

	if ('.' in name && File( // use FILE
			"${vm.functions.stdlibPath}/${name.split('.')[0]}/${
				name.split(
					'.'
				)[1]
			}.lib"
		).exists()
	) {
		val path = File(
			"${vm.functions.stdlibPath}/${name.split('.')[0]}/${
				name.split(
					'.'
				)[1]
			}.lib"
		).absolutePath
		return path
	}
	return null
}