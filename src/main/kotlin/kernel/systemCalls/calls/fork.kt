package kernel.systemCalls.calls


import environment.reflection.reflection
import kernel.systemCalls.SystemCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Runtime.getRuntime

private const val javaEx =
	"/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/build/libs/MVM-1.0.jar"

suspend fun SystemCall.fork() {

	val ss = "java -jar $javaEx irun ${reflection.currentFileData.name}"
	forkAndExecute(ss)

}

private suspend fun forkAndExecute(
	programPath: String,
): Unit = withContext(Dispatchers.IO) {
	try {
		val process = getRuntime().exec(programPath)

		CoroutineScope(Dispatchers.IO).launch {
			process.inputStream.bufferedReader().useLines { lines ->
				lines.forEach { line ->
					if (line != javaEx) println(line)
				}
			}
		}

		CoroutineScope(Dispatchers.IO).launch {
			process.errorStream.bufferedReader().useLines { lines ->
				lines.forEach { line ->
					println(line)
				}
			}
		}
//		process.waitFor()


	} catch (e: IOException) {
		println("Error: ${e.message}")
	}
}
