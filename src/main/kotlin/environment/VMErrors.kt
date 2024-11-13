package environment

import data.memory.MemoryAddress
import data.registers.RegisterType
import internals.Vm
import internals.instructions.misc.HelpJsonArguments
import kotlin.system.exitProcess

/**
 * Handles runtime errors within the virtual machine.
 *
 * This class provides a set of functions for reporting various types of errors encountered during VM execution,
 * printing error messages to the standard error stream, and terminating the VM with specific exit codes.
 */
@Suppress("unused")
class VMErrors(val vm: Vm) {
	/**
	 * Reports an invalid register exception.
	 *
	 * @param message A description of the invalid register type.
	 */
	fun InvalidRegisterException(message: String) {
		System.err.println("${prefix()}: Invalid Register of type \"$message\"")
		exitProcess(1)
	}

	/**
	 * Reports an invalid memory address exception.
	 *
	 * @param message The invalid [MemoryAddress] accessed.
	 */
	fun InvalidMemoryAddressException(message: MemoryAddress) {
		System.err.println("${prefix()}: Invalid Memory Address \"${message.address}\"")
		exitProcess(2)
	}

	/**
	 * Reports an invalid memory address exception.
	 *
	 * @param message The invalid [MemoryAddress] in string form.
	 */
	fun InvalidMemoryAddressException(message: String) {
		System.err.println("${prefix()}: Invalid Memory Address \"${message}\"")
		exitProcess(2)
	}


	/**
	 * Reports an invalid instruction exception.
	 *
	 * @param message The invalid instruction mnemonic encountered.
	 */
	fun InvalidInstructionException(message: String) {
		System.err.println("${prefix()}: Invalid Instruction \"${message}\"")
		exitProcess(3)
	}

	/**
	 * Reports an invalid system call exception.
	 *
	 * @param message A description of the invalid system call.
	 */
	fun InvalidSystemCallException(message: String) {
		System.err.println("${prefix()}: Invalid System Call \"${message}\"")
		exitProcess(4)
	}

	/**
	 * Reports a stack overflow exception.
	 */
	fun StackOverflowException() {
		System.err.println("${prefix()}: Stack Overflow Exception")
		exitProcess(5)
	}

	/**
	 * Reports an empty stack exception.
	 */
	fun EmptyStackException() {
		System.err.println("${prefix()}: Empty Stack Exception")
		exitProcess(6)
	}

	/**
	 * Reports a general arithmetic exception.
	 *
	 * @param message A description of the arithmetic error (e.g. "division by zero").
	 */
	fun GeneralArithmeticException(message: String) {
		System.err.println("${prefix()}: General Arithmetic Exception \"$message operation failed\"")
		exitProcess(7)
	}

	/**
	 * Reports a general system call exception.
	 *
	 * @param message A description of the system call error.
	 */
	fun SystemCallGeneralException(message: String, info: String? = null) {
		if (info == null) {
			System.err.println("${prefix()}: System Call General Exception \"$message operation failed\"")
		} else {
			System.err.println("${prefix()}: System Call General Exception \"$message\" \"$info\"")
		}
		exitProcess(8)
	}

	/**
	 * Reports a file access exception.
	 */
	fun FileAccessException() {
		System.err.println("${prefix()}: File Access Exception")
		exitProcess(9)
	}

	/**
	 * Reports a socket exception.
	 */
	fun SocketException() {
		System.err.println("${prefix()}: Socket Exception")
		exitProcess(10)
	}

	/**
	 * Reports a memory allocation exception.
	 *
	 * @param message A description of the memory allocation error.
	 */
	fun MemoryAllocationException(message: String) {
		System.err.println("${prefix()}: Memory Allocation Exception \"$message\"")
		exitProcess(11)
	}

	/**
	 * Reports an invalid instruction argument exception.
	 *
	 * @param message A description of the invalid argument.
	 */
	fun InvalidInstructionArgumentException(message: String) {
		System.err.println("${prefix()}: Invalid Instruction Argument \"$message\"")
		exitProcess(12)
	}

	/**
	 * Reports a null register exception.
	 *
	 * @param message The [RegisterType] of the register that was null.
	 */
	fun NullRegisterException(message: RegisterType) {
		System.err.println("${prefix()}: Null Register of \"$message\"")
		exitProcess(13)
	}

	/**
	 * Reports a null address exception.
	 *
	 * @param message The null [MemoryAddress] that was accessed.
	 */
	fun NullAddressException(message: MemoryAddress) {
		System.err.println("${prefix()}: Null Address of \"${message.address}\"")
		exitProcess(14)
	}

	/**
	 * Reports an invalid file descriptor exception.
	 *
	 * @param message A description of the invalid file descriptor.
	 */
	fun InvalidFileDescriptorException(message: String) {
		System.err.println("${prefix()}: Invalid File Descriptor of \"$message\"")
		exitProcess(15)
	}

	/**
	 * Reports a not-free memory exception.
	 *
	 * @param message The memory address that was not free.
	 */
	fun NotFreeMemoryException(message: String) {
		System.err.println("${prefix()}: Address \"$message\" is not free Memory")
		exitProcess(16)
	}

	/**
	 * Reports a general bitwise exception.
	 *
	 * @param message A description of the bitwise operation error.
	 */
	fun GeneralBitwiseException(message: String) {
		System.err.println("${prefix()}: General Bitwise Exception \"$message operation failed\"")
		exitProcess(17)
	}

	/**
	 * Reports a general control flow exception.
	 *
	 * @param message A description of the control flow error.
	 */
	fun GeneralControlFlowException(message: String) {
		System.err.println("${prefix()}: General ControlFlow Exception \"$message operation failed\"")
		exitProcess(18)
	}

	/**
	 * Reports a general data transfer exception.
	 *
	 * @param message A description of the data transfer error.
	 */
	fun GeneralDataTransferException(message: String) {
		System.err.println("${prefix()}: General DataTransfer Exception \"$message operation failed\"")
		exitProcess(19)
	}

	/**
	 * Reports a general I/O abstractions exception.
	 *
	 * @param message A description of the I/O error.
	 */
	fun GeneralIoAbstractionsException(message: String) {
		System.err.println("${prefix()}: General IoAbstractions Exception \"$message operation failed\"")
		exitProcess(20)
	}

	/**
	 * Reports a general memory exception.
	 *
	 * @param message A description of the memory operation error.
	 */
	fun GeneralMemoryException(message: String) {
		System.err.println("${prefix()}: General Memory Exception \"$message operation failed\"")
		exitProcess(21)
	}

	/**
	 * Reports a general stack operations exception.
	 *
	 * @param message A description of the stack operation error.
	 */
	fun GeneralStackOperationsException(message: String) {
		System.err.println("${prefix()}: General Stack Operation \"$message operation failed\"")
		exitProcess(22)
	}

	/**
	 * Reports a general string exception.
	 *
	 * @param message A description of the string operation error.
	 */
	fun GeneralStringException(message: String) {
		System.err.println("${prefix()}: General Strings Exception \"$message operation failed\"")
		exitProcess(23)
	}

	fun InvalidArgumentException(info: HelpJsonArguments) {
		System.err.println("${prefix()}:Missing argument Exception: \"${info.name}\"")
		exitProcess(24)
	}

	fun InvalidArgumentFormatException(badType: String, shouldBe: String) {
		System.err.println("${prefix()}:Invalid Argument Format Exception: Invalid type of \"$badType\", should be \"$shouldBe\"")
		exitProcess(25)
	}

	fun MissingLibraryException(message: String) {
		System.err.println("${prefix()}: Missing Library Exception \"$message\"")
		exitProcess(26)
	}

	fun FileAlreadyExistsException(message: String) {
		System.err.println("${prefix()}: File Already Exists Exception: \"$message\"")
		exitProcess(27)
	}

	fun FileNotFoundException(message: String) {
		System.err.println("${prefix()}: File Not Found Exception: \"$message\"")
		exitProcess(28)
	}

	fun BadSymbolAtRuntimeException(message: String) {
		System.err.println("ERROR:${vm.pc - 1}: Bad Symbol at Runtime Exception in MAR \"${message}\"")
		exitProcess(29)
	}

	fun InvalidRegisterTypeException(message: String) {
		System.err.println("${prefix()}: Invalid Register Type Exception: \"$message\"")
		exitProcess(30)
	}

}


private fun VMErrors.prefix(): String {
	return if (vm.libExecute.enabledFunction) {
		"ERROR in ${vm.libExecute.currentFunction.removeSuffix(".lib")}:${vm.libPc}:${vm.pc}"
	} else {
		"ERROR:${vm.pc}"
	}
}