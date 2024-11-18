import loader.Parser
import loader.ProgramLoader
import processes.Pcb
import vm.Memory
import java.io.File

val config = Config()
val memory = Memory()

val init = Pcb(
	pid = 1,
	programPath = "src/main/resources/programs/main.kar",
)

fun main() {


	val parser = Parser()
	val programLoader = ProgramLoader(init)
	programLoader.toInstructionMemory(parser.parseFile(File(init.programPath)))

	for (i in 0..7) {
		println(memory[i, init])
	}


//	programLoader.toInstructionMemory(parser.parseFile(File(init.programPath)))
//	println(memory[3, init])


}