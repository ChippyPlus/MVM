package environment

import data.registers.RegisterType
import internals.Vm
import internals.instructions.misc.HelpJsonArguments
import kotlin.system.exitProcess


@Suppress("unused")
class VMErrors(val vm: Vm? = null, val pc: Long? = null) {
	fun InvalidRegisterException(message: String) {
		System.err.println("${prefix()}: Invalid Register of type \"$message\"")
		exitProcess(1)
	}

	fun InvalidMemoryAddressException(message: Long) {
		System.err.println("${prefix()}: Invalid Memory Address \"${message}\"")
		exitProcess(2)
	}

	fun InvalidMemoryAddressException(message: String) {
		System.err.println("${prefix()}: Invalid Memory Address \"${message}\"")
		exitProcess(2)
	}

	fun InvalidInstructionException(message: String) {
		System.err.println("${prefix()}: Invalid Instruction \"${message}\"")
		exitProcess(3)
	}

	fun InvalidSystemCallException(message: String) {
		System.err.println("${prefix()}: Invalid System Call \"${message}\"")
		exitProcess(4)
	}

	fun StackOverflowException() {
		System.err.println("${prefix()}: Stack Overflow Exception")
		exitProcess(5)
	}

	fun EmptyStackException() {
		System.err.println("${prefix()}: Empty Stack Exception")
		exitProcess(6)
	}


	fun GeneralArithmeticException(message: String) {
		System.err.println("${prefix()}: General Arithmetic Exception \"$message operation failed\"")
		exitProcess(7)
	}


	fun SystemCallGeneralException(message: String, info: String? = null) {
		if (info == null) {
			System.err.println("${prefix()}: System Call General Exception \"$message operation failed\"")
		} else {
			System.err.println("${prefix()}: System Call General Exception \"$message\" \"$info\"")
		}
		exitProcess(8)
	}


	fun FileAccessException() {
		System.err.println("${prefix()}: File Access Exception")
		exitProcess(9)
	}

	fun SocketException() {
		System.err.println("${prefix()}: Socket Exception")
		exitProcess(10)
	}

	fun MemoryAllocationException(message: String) {
		System.err.println("${prefix()}: Memory Allocation Exception \"$message\"")
		exitProcess(11)
	}

	fun InvalidInstructionArgumentException(message: String) {
		System.err.println("${prefix()}: Invalid Instruction Argument \"$message\"")
		exitProcess(12)
	}

	fun NullRegisterException(message: RegisterType) {
		System.err.println("${prefix()}: Null Register of \"$message\"")
		exitProcess(13)
	}

	fun NullAddressException(message: Long) {
		System.err.println("${prefix()}: Null Address of \"${message}\"")
		exitProcess(14)
	}

	fun InvalidFileDescriptorException(message: String) {
		System.err.println("${prefix()}: Invalid File Descriptor of \"$message\"")
		exitProcess(15)
	}

	fun NotFreeMemoryException(message: String) {
		System.err.println("${prefix()}: Address \"$message\" is not free Memory")
		exitProcess(16)
	}

	fun GeneralBitwiseException(message: String) {
		System.err.println("${prefix()}: General Bitwise Exception \"$message operation failed\"")
		exitProcess(17)
	}

	fun GeneralControlFlowException(message: String) {
		System.err.println("${prefix()}: General ControlFlow Exception \"$message operation failed\"")
		exitProcess(18)
	}

	fun GeneralDataTransferException(message: String) {
		System.err.println("${prefix()}: General DataTransfer Exception \"$message operation failed\"")
		exitProcess(19)
	}

	fun GeneralIoAbstractionsException(message: String) {
		System.err.println("${prefix()}: General IoAbstractions Exception \"$message operation failed\"")
		exitProcess(20)
	}

	fun GeneralMemoryException(message: String) {
		System.err.println("${prefix()}: General Memory Exception \"$message operation failed\"")
		exitProcess(21)
	}

	fun GeneralStackOperationsException(message: String) {
		System.err.println("${prefix()}: General Stack Operation \"$message operation failed\"")
		exitProcess(22)
	}

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
		System.err.println("${prefix()}: Bad Symbol at Runtime Exception in MAR \"${message}\"")
		exitProcess(29)
	}

	fun InvalidRegisterTypeException(message: String) {
		System.err.println("${prefix()}: Invalid Register Type Exception: \"$message\"")
		exitProcess(30)
	}

	fun InvalidPcValueException(message: String) {
		System.err.println("${prefix()}: Invalid Pc Value Exception: \"$message\"")
		exitProcess(31)
	}

	fun ProcessNotFoundException(message: String) {
		System.err.println("${prefix()}: Process Not Found Exception: \"$message\"")
		exitProcess(32)
	}


	fun InvalidOffsetForBlockException(message: Long) {
		System.err.println("${prefix()}: Invalid Offset For Block Exception: \"$message\"")
		exitProcess(33)
	}

	fun AccessingUnallocatedMemoryException(message: Long) {
		System.err.println("${prefix()}: Accessing Unallocated Memory Exception: \"$message\"")
	}



	private fun prefix(): String {
		return if (vm != null) {
			"ERROR:${vm.pc}"
		} else {
			"ERROR:$pc"
		}
//		 Let's just forget about line numbers for now
	}

}


