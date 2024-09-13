package org.example.environment

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.kvm
import kotlin.system.exitProcess

/**
 * A class for handling and reporting runtime errors encountered in the virtual machine.
 *
 *  This class provides functions for printing error messages to the standard error stream
 *  and terminating the virtual machine execution with a specific exit code for each type of error.
 */
class VMErrors {
    private val prefix = "ERROR:"


    /**
     * Reports an invalid register exception and terminates the virtual machine.
     *
     * @param message The message describing the invalid register.
     */
    fun InvalidRegisterException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Register of type \"$message\"")
        exitProcess(1)
    }

    /**
     * Reports an out-of-bounds memory address exception and terminates the virtual machine.
     *
     * @param message The out-of-bounds memory address. Please use the address that overflowed as [message]
     */
    fun InvalidMemoryAddressException(message: MemoryAddress) {
        System.err.println("$prefix${kvm.pc}: Out Of Bounds Memory Address \"${message.address}\"")
        exitProcess(2)
    }

    /**
     * Reports an invalid instruction exception and terminates the virtual machine.
     *
     * @param instruction The invalid instruction.
     */
    fun InvalidInstructionException(instruction: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Instruction \"${instruction}\"")
        exitProcess(3)
    }

    /**
     * Reports a stack overflow exception and terminates the virtual machine.
     */
    fun StackOverflowException() {
        System.err.println("$prefix${kvm.pc}: Stack Overflow Exception")
        exitProcess(4)
    }

    /**
     * Reports an empty stack exception and terminates the virtual machine.
     */
    fun EmptyStackException() {
        System.err.println("$prefix${kvm.pc}: Empty Stack Exception")
        exitProcess(5)
    }

    /**
     * Reports an arithmetic exception and terminates the virtual machine.
     *
     * @param message An optional message describing the arithmetic error.
     */
    fun ArithmeticException(message: String? = null) {
        if (message != null) {
            System.err.println("$prefix${kvm.pc}: Arithmetic Exception \"$message\"")
        } else {
            System.err.println("$prefix${kvm.pc}: Arithmetic Exception")
        }
        exitProcess(6)
    }


    /**
     * Reports a system call exception and terminates the virtual machine.
     * @param message An optional message describing the system call error.
     */
    fun SystemCallException(message: String? = null) {
        if (message != null) {
            System.err.println("$prefix${kvm.pc}: System Call Exception \"$message\"")
        } else {
            System.err.println("$prefix${kvm.pc}: System Call Exception")
        }
        exitProcess(7)
    }

    /**
     * Reports a file access exception and terminates the virtual machine.
     */
    fun FileAccessException() {
        System.err.println("$prefix${kvm.pc}: File Access Exception")
        exitProcess(8)
    }

    /**
     * Reports a socket exception and terminates the virtual machine.
     */
    fun SocketException() {
        System.err.println("$prefix${kvm.pc}: Socket Exception")
        exitProcess(9)
    }

    /**
     * Reports a memory allocation exception and terminates the virtual machine.
     * @param message An optional message describing the memory error.
     */
    fun MemoryAllocationException(message: String? = null) {
        if (message != null) {
            System.err.println("$prefix${kvm.pc}: Memory Allocation Exception \"$message\"")
        } else {
            System.err.println("$prefix${kvm.pc}: Memory Allocation Exception")
        }
        exitProcess(10)
    }

    /**
     * Reports an invalid instruction argument exception and terminates the virtual machine.
     *
     * @param message The message describing the invalid argument.
     */
    fun InvalidInstructionArgumentException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid Instruction Argument \"$message\"")
        exitProcess(11)
    }

    /**
     * Reports a null register exception and terminates the virtual machine.
     *
     * @param message The message describing the null register.
     */
    fun NullRegisterException(message: SuperRegisterType) {
        System.err.println("$prefix${kvm.pc}: Null Register of \"$message\"")
        exitProcess(12)
    }

    fun NullAddressException(message: MemoryAddress) {
        System.err.println("$prefix${kvm.pc}: Null Address of \"$message\"")
        exitProcess(13)
    }

    fun InvalidFileDescriptorException(message: String) {
        System.err.println("$prefix${kvm.pc}: Invalid File Descriptor of \"$message\"")
        exitProcess(14)
    }
}