package internals.systemCalls.calls.legacy

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R2
import fileDescriptors
import helpers.VMFile
import helpers.readRegisterString
import helpers.registerWrite
import internals.systemCalls.SystemCall
import java.io.File

/**
 * Opens a file specified by the path.
 *
 * System call number: 3
 *
 * @param registerPath The register containing the path to the file to open (stored in register S1).
 */
@Deprecated("Using new VFS")
fun SystemCall.openFile(registerPath: SuperRegisterType) {
	val path: String = readRegisterString(register = registerPath)
	val f = File(path)
	@Suppress("UNUSED_VARIABLE") val fd: Long = fileDescriptors.addFileDescriptor(fileName = VMFile(f)).apply {
		registerWrite(
			register = R2,
			value = this@apply,
		)
	}

}