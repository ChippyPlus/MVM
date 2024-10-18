package internals.instructions.misc

import engine.parser
import vm

fun Misc.catch() {
	val currentUnaffected = vm.pc
	val problemLine = parser()
}