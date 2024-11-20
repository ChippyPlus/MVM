package internals.systemCalls

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong
import internals.Vm
import internals.systemCalls.calls.*
import kotlin.system.exitProcess

/**
 * Handles the execution of system calls within the virtual machine.
 */
class SystemCall(val vm: Vm) {

	val helpers = vm.helpers
	val internalMemory = vm.internalMemory
	val errors = vm.errors
	val registers = vm.registers

	/**
	 * Executes the system call specified by the call ID.
	 *
	 * @param callId The system call number, identifying the system call to execute (stored in register S0).
	 * @param s2 The register containing the first argument to the system call (stored in register S1).
	 * @param s3 The register containing the second argument to the system call (stored in register S2).
	 * @param s4 The register containing the third argument to the system call (stored in register S3).
	 * @throws InvalidSystemCallException If the provided system call number is invalid.
	 */
	@Suppress("UNUSED_EXPRESSION")
	fun execute(
		callId: RegisterType,
		s2: RegisterType,
		s3: RegisterType,
		s4: RegisterType,
	) {
		when (registers.read(callId).toInt()) {
			1 -> newFile(s2)
			2 -> readFile(s2)
			3 -> writeFile(s2, s3)
			4 -> listFiles()
			5 -> deleteFile(s2)
			6 -> exit(s2)
			7 -> exec(s2)
			8 -> fork()
			9 -> spawn(s2)
			10 -> share_m(s2, s3, s4)
			11 -> pause_t(s2)
			12 -> continue_t(s2)
			14 -> time()
			16 -> getPid()
			17 -> getUid()
			18 -> handleSignals(s2, s3)
			19 -> sendSignal(s2, s3)
			24 -> writeIo(s2)
			25 -> readIo()
			26 -> createArray(s2)
			27 -> arraySet(s2, s3, s4)
			28 -> arrayGet(s2, s3)

			30 -> Ipc(vm).link(s2, s3)
			31 -> "unlink_pro"
			32 -> Ipc(vm).send(s2, s3)
			33 -> Ipc(vm).receive(s2)


			else -> errors.InvalidSystemCallException(registers.read(callId).toString())
		}
	}


	inline fun call(name: String, function: () -> Unit?) {

		val functionResult = try {
			function()
		} catch (_: Exception) {
			errors.SystemCallGeneralException(message = name)
			exitProcess(29384)
		}

		if (functionResult != null) {
			registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		} else {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
		}
	}
}