package environment

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import kvm
import kotlin.system.exitProcess

@Suppress("unused")
class VMErrors {
    private val prefix = "ERROR:"

    fun InvalidRegisterException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Register of type \"$message\"")
        exitProcess(1)
    }

    fun InvalidMemoryAddressException(message: MemoryAddress) {
        System.err.println("$prefix${kvm.pc}: Out Of Bounds Memory Address \"${message.address}\"")
        exitProcess(2)
    }

    fun InvalidInstructionException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Instruction \"${message}\"")
        exitProcess(3)
    }

    fun InvalidSystemCallException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid System Call \"${message}\"")
        exitProcess(4)
    }

    fun StackOverflowException() {
        System.err.println("$prefix${kvm.pc}: Stack Overflow Exception")
        exitProcess(5)
    }

    fun EmptyStackException() {
        System.err.println("$prefix${kvm.pc}: Empty Stack Exception")
        exitProcess(6)
    }

    fun GeneralArithmeticException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Arithmetic Exception \"$message operation failed\"")
        exitProcess(7)
    }


    fun SystemCallGeneralException(message: String) {
        System.err.println("$prefix${kvm.pc}: System Call General Exception \"$message operation failed\"")
        exitProcess(8)
    }

    fun FileAccessException() {
        System.err.println("$prefix${kvm.pc}: File Access Exception")
        exitProcess(9)
    }

    fun SocketException() {
        System.err.println("$prefix${kvm.pc}: Socket Exception")
        exitProcess(10)
    }

    fun MemoryAllocationException(message: String) {
        System.err.println("$prefix${kvm.pc}: Memory Allocation Exception \"$message\"")
        exitProcess(11)
    }

    fun InvalidInstructionArgumentException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Instruction Argument \"$message\"")
        exitProcess(12)
    }

    fun NullRegisterException(message: SuperRegisterType) {
        System.err.println("$prefix${kvm.pc}: Null Register of \"$message\"")
        exitProcess(13)
    }

    fun NullAddressException(message: MemoryAddress) {
        System.err.println("$prefix${kvm.pc}: Null Address of \"$message\"")
        exitProcess(14)
    }

    fun InvalidFileDescriptorException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid File Descriptor of \"$message\"")
        exitProcess(15)
    }

    fun NotFreeMemoryException(message: String) {
        System.err.println("$prefix${kvm.pc}: Address \"$message\" is not free Memory")
        exitProcess(16)
    }

    fun GeneralBitwiseException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Bitwise Exception \"$message operation failed\"")
        exitProcess(17)
    }

    fun GeneralControlFlowException(message: String) {
        System.err.println("$prefix${kvm.pc}: General ControlFlow Exception \"$message operation failed\"")
        exitProcess(18)
    }

    fun GeneralDataTransferException(message: String) {
        System.err.println("$prefix${kvm.pc}: General DataTransfer Exception \"$message operation failed\"")
        exitProcess(19)
    }

    fun GeneralIoAbstractionsException(message: String) {
        System.err.println("$prefix${kvm.pc}: General IoAbstractions Exception \"$message operation failed\"")
        exitProcess(20)
    }

    fun GeneralMemoryException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Memory Exception \"$message operation failed\"")
        exitProcess(21)
    }

    fun GeneralStackOperationsException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Stack Operation \"$message operation failed\"")
        exitProcess(22)
    }

    fun GeneralStringException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Strings Exception \"$message operation failed\"")
        exitProcess(23)
    }


}