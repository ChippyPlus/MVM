package enviroment

import data.memory.MemoryAddress
import data.registers.enumIdenifies.RegisterType
import mvm
import kotlin.system.exitProcess

/**
 * Handles runtime errors within the virtual machine.
 *
 * This class provides a set of functions for reporting various types of errors encountered during VM execution,
 * printing error messages to the standard error stream, and terminating the VM with specific exit codes.
 */
class Errors {
    private val prefix = "ERROR:"

    /**
     * Reports an invalid register exception.
     *
     * @param message A description of the invalid register type.
     */
    fun InvalidRegisterException(message: RegisterType) {
        System.err.println("$prefix${mvm.pc}: Invalid Register of type \"$message\"")
        exitProcess(1)
    }

    /**
     * Reports an invalid memory address exception.
     *
     * @param message The invalid [MemoryAddress] accessed.
     */
    fun InvalidMemoryAddressException(message: MemoryAddress) {
        System.err.println("$prefix${mvm.pc}: Out Of Bounds Memory Address \"${message.address}\"")
        exitProcess(2)
    }

    /**
     * Reports an invalid instruction exception.
     *
     * @param message The invalid instruction mnemonic encountered.
     */
    fun InvalidInstructionException(message: String) {
        System.err.println("$prefix${mvm.pc}: Invalid Instruction \"${message}\"")
        exitProcess(3)
    }

    /**
     * Reports an invalid system call exception.
     *
     * @param message A description of the invalid system call.
     */
    fun InvalidSystemCallException(message: String) {
        System.err.println("$prefix${mvm.pc}: Invalid System Call \"${message}\"")
        exitProcess(4)
    }

    /**
     * Reports a stack overflow exception.
     */
    fun StackOverflowException() {
        System.err.println("$prefix${mvm.pc}: Stack Overflow Exception")
        exitProcess(5)
    }

    /**
     * Reports an empty stack exception.
     */
    fun EmptyStackException() {
        System.err.println("$prefix${mvm.pc}: Empty Stack Exception")
        exitProcess(6)
    }

    /**
     * Reports a general arithmetic exception.
     *
     * @param message A description of the arithmetic error (e.g., "division by zero").
     */
    fun GeneralArithmeticException(message: String) {
        System.err.println("$prefix${mvm.pc}: General Arithmetic Exception \"$message operation failed\"")
        exitProcess(7)
    }

    /**
     * Reports a general system call exception.
     *
     * @param message A description of the system call error.
     */
    fun SystemCallGeneralException(message: String) {
        System.err.println("$prefix${mvm.pc}: System Call General Exception \"$message operation failed\"")
        exitProcess(8)
    }

    /**
     * Reports a file access exception.
     */
    fun FileAccessException() {
        System.err.println("$prefix${mvm.pc}: File Access Exception")
        exitProcess(9)
    }

    /**
     * Reports a socket exception.
     */
    fun SocketException() {
        System.err.println("$prefix${mvm.pc}: Socket Exception")
        exitProcess(10)
    }

    /**
     * Reports a memory allocation exception.
     *
     * @param message A description of the memory allocation error.
     */
    fun MemoryAllocationException(message: String? = null) {
        if (message != null) {
            System.err.println("$prefix${mvm.pc}: Memory Allocation Exception \"$message\"")
        } else {
            System.err.println("$prefix${mvm.pc}: Memory Allocation Exception")
        }
        exitProcess(11)
    }

    /**
     * Reports an invalid instruction argument exception.
     *
     * @param message A description of the invalid argument.
     */
    fun InvalidInstructionArgumentException(message: String) {
        System.err.println("$prefix${mvm.pc}: Invalid Instruction Argument \"$message\"")
        exitProcess(12)
    }

    /**
     * Reports a null register exception.
     *
     * @param message The [RegisterType] of the register that was null.
     */
    fun NullRegisterException(message: RegisterType) {
        System.err.println("$prefix${mvm.pc}: Null Register of \"$message\"")
        exitProcess(13)
    }

    /**
     * Reports a null address exception.
     *
     * @param message The null [MemoryAddress] that was accessed.
     */
    fun NullAddressException(message: MemoryAddress) {
        System.err.println("$prefix${mvm.pc}: Null Address of \"$message\"")
        exitProcess(14)
    }

    /**
     * Reports an invalid file descriptor exception.
     *
     * @param message A description of the invalid file descriptor.
     */
    fun InvalidFileDescriptorException(message: String) {
        System.err.println("$prefix${mvm.pc}: Invalid File Descriptor of \"$message\"")
        exitProcess(15)
    }

    /**
     * Reports a not-free memory exception.
     *
     * @param message The memory address that was not free.
     */
    fun NotFreeMemoryException(message: String) {
        System.err.println("$prefix${mvm.pc}: Address \"$message\" is not free Memory")
        exitProcess(16)
    }

    /**
     * Reports a general bitwise exception.
     *
     * @param message A description of the bitwise operation error.
     */
    fun GeneralBitwiseException(message: String) {
        System.err.println("$prefix${mvm.pc}: General Bitwise Exception \"$message operation failed\"")
        exitProcess(17)
    }

    /**
     * Reports a general control flow exception.
     *
     * @param message A description of the control flow error.
     */
    fun GeneralControlFlowException(message: String) {
        System.err.println("$prefix${mvm.pc}: General ControlFlow Exception \"$message operation failed\"")
        exitProcess(18)
    }

    /**
     * Reports a general data transfer exception.
     *
     * @param message A description of the data transfer error.
     */
    fun GeneralDataTransferException(message: String) {
        System.err.println("$prefix${mvm.pc}: General DataTransfer Exception \"$message operation failed\"")
        exitProcess(19)
    }

    /**
     * Reports a general I/O abstractions exception.
     *
     * @param message A description of the I/O error.
     */
    fun GeneralIoAbstractionsException(message: String) {
        System.err.println("$prefix${mvm.pc}: General IoAbstractions Exception \"$message operation failed\"")
        exitProcess(20)
    }

    /**
     * Reports a general memory exception.
     *
     * @param message A description of the memory operation error.
     */
    fun GeneralMemoryException(message: String) {
        System.err.println("$prefix${mvm.pc}: General Memory Exception \"$message operation failed\"")
        exitProcess(21)
    }

    /**
     * Reports a general stack operations exception.
     *
     * @param message A description of the stack operation error.
     */
    fun GeneralStackOperationsException(message: String) {
        System.err.println("$prefix${mvm.pc}: General Stack Operation \"$message operation failed\"")
        exitProcess(22)
    }

    /**
     * Reports a general string exception.
     *
     * @param message A description of the string operation error.
     */
    fun GeneralStringException(message: String) {
        System.err.println("$prefix${mvm.pc}: General Strings Exception \"$message operation failed\"")
        exitProcess(23)
    }

    /**
     * Reports a type mismatch exception. This occurs when there's a mismatch between the expected
     * and actual data types during an operation.
     *
     * @param message A description of the type mismatch, including the expected and actual types.
     */
    fun TypeMismatchException(message: String) {
        System.err.println("$prefix${mvm.pc}: Type Mismatch Exception: $message")
        exitProcess(24)
    }

    /**
     * Reports a register overflow exception. This happens when an attempt is made to store a
     * value that exceeds the capacity of the target register.
     *
     * @param message A description of the overflow, including the value and register involved.
     */
    fun RegisterOverflowException(message: String) {
        System.err.println("$prefix${mvm.pc}: Register Overflow Exception: $message")
        exitProcess(25)
    }

    /**
     * Reports an invalid array index exception. This occurs when an array index is out of bounds.
     *
     * @param message A description of the invalid index, including the array and index used.
     */
    fun InvalidArrayIndexException(message: String) {
        System.err.println("$prefix${mvm.pc}: Invalid Array Index Exception: $message")
        exitProcess(26)
    }

    /**
     * Reports a division by zero exception.
     *
     * @param message A message indicating the occurrence of division by zero.
     */
    fun DivisionByZeroException(message: String = "Division by zero attempted.") {
        System.err.println("$prefix${mvm.pc}: Division By Zero Exception: $message")
        exitProcess(27)
    }

    /**
     * Reports a null pointer exception. This happens when trying to access a memory location
     * through a null pointer (a register containing a null value as a memory address).
     *
     * @param message A description of the null pointer access, including the register involved.
     */
    fun NullPointerException(message: String) {
        System.err.println("$prefix${mvm.pc}: Null Pointer Exception: $message")
        exitProcess(28)
    }

    /**
     * Reports a non-initialized register exception. This occurs when trying to read from a
     * register that has not been assigned a value.
     *
     * @param message The register that was accessed without initialization.
     */
    fun NonInitializedRegisterException(message: String) {
        System.err.println("$prefix${mvm.pc}: Non-Initialized Register Exception: $message")
        exitProcess(29)
    }

    /**
     * Reports an illegal instruction format exception. This occurs when the format of an
     * instruction does not conform to the expected syntax.
     *
     * @param message A description of the illegal instruction format.
     */
    fun IllegalInstructionFormatException(message: String) {
        System.err.println("$prefix${mvm.pc}: Illegal Instruction Format Exception: $message")
        exitProcess(30)
    }
}