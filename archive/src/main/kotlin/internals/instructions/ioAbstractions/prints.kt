package internals.instructions.ioAbstractions

import errors
import vm

/**
 * Prints the value at the top of the stack to the console.
 *
 * @throws GeneralIoAbstractionsException If an error occurs during the printing operation.
 */
fun IoAbstractions.prints(): Unit = try {
	println(message = with(vm) {
		return@with this.stackOperations.run { return@run this@run.internalStack.peek() }
	})
} catch (_: Exception) {
	errors.run {
		this@run.GeneralIoAbstractionsException(message = "prints")
	}
}