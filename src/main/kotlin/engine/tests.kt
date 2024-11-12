package engine

import data.registers.RegisterType
import engine.execution.Execute
import engine.execution.InstructData
import internals.Pc
import kotlinx.coroutines.*


var pids = 0

data class MProcess(val instructions: List<InstructData>) {
	val pc = Pc()
}

val pp1 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 20L)),
	InstructData("lit", arrayOf(RegisterType.G2, 30L)),
	InstructData("add", arrayOf(RegisterType.G1, RegisterType.G2)),
	InstructData("printr", arrayOf(RegisterType.R4))
)
val pp2 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 11L)),
	InstructData("lit", arrayOf(RegisterType.G2, 0L)),
	InstructData("store", arrayOf(RegisterType.G1, RegisterType.G2)),
	InstructData("load", arrayOf(RegisterType.G2, RegisterType.R3)),
	InstructData("printr", arrayOf(RegisterType.R3))
)
val pp3 = listOf(
	InstructData("str", arrayOf(RegisterType.F1, "Hello, World!")),
	InstructData("lit", arrayOf(RegisterType.R4, 0L)),
	InstructData("lit", arrayOf(RegisterType.G2, 1L)),
	InstructData("lit", arrayOf(RegisterType.G3, 5L)),
	InstructData("eq", arrayOf(RegisterType.R4, RegisterType.G3)),
	InstructData("jnz", arrayOf(10L)),
	InstructData("add", arrayOf(RegisterType.R4, RegisterType.G2)),
	InstructData("call", arrayOf("println")),
	InstructData("jmp", arrayOf(5L)),
	InstructData("lit", arrayOf(RegisterType.F1, 0L)),
	InstructData("call", arrayOf("sys.exit")),
)
val pp4 = listOf(
	InstructData("xlit", arrayOf(RegisterType.X1, "3.14159")),
	InstructData("xlit", arrayOf(RegisterType.X2, "2.71828")),
	InstructData("xmul", arrayOf(RegisterType.X1, RegisterType.X2)),
	InstructData("cpy", arrayOf(RegisterType.R5, RegisterType.F1)),
	InstructData("call", arrayOf("strings.cheekyfloat"))
)

val p1 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 1000L)),
	InstructData("sleep", arrayOf(RegisterType.G1)),
	InstructData("str", arrayOf(RegisterType.F1, "Process 1")),
	InstructData("call", arrayOf("println"))
)
val p2 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 1000L)),
	InstructData("sleep", arrayOf(RegisterType.G1)),
	InstructData("str", arrayOf(RegisterType.F1, "Process 2")),
	InstructData("call", arrayOf("println"))
)
val p3 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 1000L)),
	InstructData("sleep", arrayOf(RegisterType.G1)),
	InstructData("str", arrayOf(RegisterType.F1, "Process 3")),
	InstructData("call", arrayOf("println"))
)
val p4 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 1000L)),
	InstructData("sleep", arrayOf(RegisterType.G1)),
	InstructData("str", arrayOf(RegisterType.F1, "Process 4")),
	InstructData("call", arrayOf("println"))
)


val processes = mutableListOf<List<InstructData>>()

val testp = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

suspend fun w(lambda: () -> Unit) = withContext(Dispatchers.IO) {
//	sleep(1000L)
//	println(i * i)
	lambda()
}


fun main() = runBlocking {

	(1..20).forEach {
		processes.add(
			listOf(
				InstructData("str", arrayOf(RegisterType.F1, "Process $it")), InstructData("call", arrayOf("println"))
			)
		)
	}


	val jobs = processes.map { i ->
		launch {
			w {
				Execute().run(i)
			}
		}
	}

	jobs.joinAll()
}