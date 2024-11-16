import processes.Pcb
import vm.Memory

val config = Config()
val memory = Memory()

val init = Pcb(
	pid = 1,
	programPath = "src/main/resources/programs/main.kar",
)

fun main() {
	val x = memory.allocate(1, init)
	memory[x, init] = 25
	val y = memory.allocate(1, init)
	memory[y, init] = 50

	val z = memory.allocate(1, init)
	memory[z, init] = 75
	println(memory[y, init])
	println(memory[x, init])
	memory.deallocate(z, init)

	println(memory[z, init])


//	val parser = Parser()
//	val programLoader = ProgramLoader()
//	programLoader.toInstructionMemory(parser.parseFile(File(init.programPath)))
//	println(memory[3, init])


}