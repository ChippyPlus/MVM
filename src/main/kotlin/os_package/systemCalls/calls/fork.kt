package os_package.systemCalls.calls


import environment.reflection.reflection
import kotlinx.coroutines.*
import os_package.systemCalls.SystemCall
import java.io.IOException
import java.lang.Runtime.getRuntime

private const val javaEx =
	"/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/build/libs/MVM-1.0.jar"

fun SystemCall.fork() {

	val ss = "java -jar $javaEx irun ${reflection.currentFileData.name}"
	runBlocking { forkAndExecute(ss) }

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
