package internals.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import internals.systemCalls.SystemCall
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.Runtime.getRuntime

private const val javaEx =
	"/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/build/libs/MVM-1.0.jar"

fun SystemCall.fork(path: RegisterType) {

	val ss = "java -jar $javaEx irun ${
		readRegisterString(path)
	}"
	runBlocking { forkAndExecute(ss) }

}

suspend fun forkAndExecute(
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
