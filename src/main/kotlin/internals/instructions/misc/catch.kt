package internals.instructions.misc

import reflection.reflectInstruction
import vm

fun Misc.catch() {
	val currentUnaffected = vm.pc
	val problemLine = reflectInstruction
}