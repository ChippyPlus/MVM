package internals.instructions.misc

import engine.execution.InstructData
import reflection.ReflectionInstructionType
import reflection.reflectInstruction
import vm

fun Misc.catch() {
	val currentUnaffected = vm.pc
	val problemLine = reflectInstruction[ReflectionInstructionType.Command] as InstructData?
	println(problemLine)
}