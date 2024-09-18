package environment

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import kvm
import kotlin.system.exitProcess

/**
 * Handles runtime errors within the virtual machine.
 *
 * This class provides a set of functions for reporting various types of errors encountered during VM execution,
 * printing error messages to the standard error stream, and terminating the VM with specific exit codes.
 */
@Suppress("unused")
class VMErrors {
    private val prefix = "ERROR:"

    /**
     * Reports an invalid register exception.
     *
     * @param message A description of the invalid register type.
     */
    fun InvalidRegisterException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Register of type \"$message\"")
        exitProcess(1)
    }

    /**
     * Reports an invalid memory address exception.
     *
     * @param message The invalid [MemoryAddress] accessed.
     */
    fun InvalidMemoryAddressException(message: MemoryAddress) {
        System.err.println("$prefix${kvm.pc}: Invalid Memory Address \"${message.address}\"")
        exitProcess(2)
    }

    /**
     * Reports an invalid memory address exception.
     *
     * @param message The invalid [MemoryAddress] in string form.
     */
    fun InvalidMemoryAddressException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Memory Address \"${message}\"")
        exitProcess(2)
    }


    /**
     * Reports an invalid instruction exception.
     *
     * @param message The invalid instruction mnemonic encountered.
     */
    fun InvalidInstructionException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Instruction \"${message}\"")
        exitProcess(3)
    }

    /**
     * Reports an invalid system call exception.
     *
     * @param message A description of the invalid system call.
     */
    fun InvalidSystemCallException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid System Call \"${message}\"")
        exitProcess(4)
    }

    /**
     * Reports a stack overflow exception.
     */
    fun StackOverflowException() {
        System.err.println("$prefix${kvm.pc}: Stack Overflow Exception")
        exitProcess(5)
    }

    /**
     * Reports an empty stack exception.
     */
    fun EmptyStackException() {
        System.err.println("$prefix${kvm.pc}: Empty Stack Exception")
        exitProcess(6)
    }

    /**
     * Reports a general arithmetic exception.
     *
     * @param message A description of the arithmetic error (e.g., "division by zero").
     */
    fun GeneralArithmeticException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Arithmetic Exception \"$message operation failed\"")
        exitProcess(7)
    }

    /**
     * Reports a general system call exception.
     *
     * @param message A description of the system call error.
     */
    fun SystemCallGeneralException(message: String) {
        System.err.println("$prefix${kvm.pc}: System Call General Exception \"$message operation failed\"")
        exitProcess(8)
    }

    /**
     * Reports a file access exception.
     */
    fun FileAccessException() {
        System.err.println("$prefix${kvm.pc}: File Access Exception")
        exitProcess(9)
    }

    /**
     * Reports a socket exception.
     */
    fun SocketException() {
        System.err.println("$prefix${kvm.pc}: Socket Exception")
        exitProcess(10)
    }

    /**
     * Reports a memory allocation exception.
     *
     * @param message A description of the memory allocation error.
     */
    fun MemoryAllocationException(message: String) {
        System.err.println("$prefix${kvm.pc}: Memory Allocation Exception \"$message\"")
        exitProcess(11)
    }

    /**
     * Reports an invalid instruction argument exception.
     *
     * @param message A description of the invalid argument.
     */
    fun InvalidInstructionArgumentException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Instruction Argument \"$message\"")
        exitProcess(12)
    }

    /**
     * Reports a null register exception.
     *
     * @param message The [SuperRegisterType] of the register that was null.
     */
    fun NullRegisterException(message: SuperRegisterType) {
        System.err.println("$prefix${kvm.pc}: Null Register of \"$message\"")
        exitProcess(13)
    }

    /**
     * Reports a null address exception.
     *
     * @param message The null [MemoryAddress] that was accessed.
     */
    fun NullAddressException(message: MemoryAddress) {
        System.err.println("$prefix${kvm.pc}: Null Address of \"$message\"")
        exitProcess(14)
    }

    /**
     * Reports an invalid file descriptor exception.
     *
     * @param message A description of the invalid file descriptor.
     */
    fun InvalidFileDescriptorException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid File Descriptor of \"$message\"")
        exitProcess(15)
    }

    /**
     * Reports a not-free memory exception.
     *
     * @param message The memory address that was not free.
     */
    fun NotFreeMemoryException(message: String) {
        System.err.println("$prefix${kvm.pc}: Address \"$message\" is not free Memory")
        exitProcess(16)
    }

    /**
     * Reports a general bitwise exception.
     *
     * @param message A description of the bitwise operation error.
     */
    fun GeneralBitwiseException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Bitwise Exception \"$message operation failed\"")
        exitProcess(17)
    }

    /**
     * Reports a general control flow exception.
     *
     * @param message A description of the control flow error.
     */
    fun GeneralControlFlowException(message: String) {
        System.err.println("$prefix${kvm.pc}: General ControlFlow Exception \"$message operation failed\"")
        exitProcess(18)
    }

    /**
     * Reports a general data transfer exception.
     *
     * @param message A description of the data transfer error.
     */
    fun GeneralDataTransferException(message: String) {
        System.err.println("$prefix${kvm.pc}: General DataTransfer Exception \"$message operation failed\"")
        exitProcess(19)
    }

    /**
     * Reports a general I/O abstractions exception.
     *
     * @param message A description of the I/O error.
     */
    fun GeneralIoAbstractionsException(message: String) {
        System.err.println("$prefix${kvm.pc}: General IoAbstractions Exception \"$message operation failed\"")
        exitProcess(20)
    }

    /**
     * Reports a general memory exception.
     *
     * @param message A description of the memory operation error.
     */
    fun GeneralMemoryException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Memory Exception \"$message operation failed\"")
        exitProcess(21)
    }

    /**
     * Reports a general stack operations exception.
     *
     * @param message A description of the stack operation error.
     */
    fun GeneralStackOperationsException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Stack Operation \"$message operation failed\"")
        exitProcess(22)
    }

    /**
     * Reports a general string exception.
     *
     * @param message A description of the string operation error.
     */
    fun GeneralStringException(message: String) {
        System.err.println("$prefix${kvm.pc}: General Strings Exception \"$message operation failed\"")
        exitProcess(23)
    }
}