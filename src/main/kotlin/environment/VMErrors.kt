package org.example.environment

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.kvmInternals.instructions.Instruction
import kotlin.system.exitProcess

class VMErrors {
    val prefix = "ERROR!!!!!!!"

    fun InvalidRegisterException(message: SuperRegisterType) {
        System.err.println("$prefix: Invalid Register of type \"$message\"")
        exitProcess(1)
    }

    fun InvalidMemoryAddressException(message: MemoryAddress) {
        System.err.println("$prefix: Out Of Bounds Memory Address \"$message\"")
        exitProcess(2)
    }

    fun InvalidInstructionException(instruction: Instruction) {
        System.err.println("$prefix: Invalid Instruction \"${instruction::class.simpleName}\"")
        exitProcess(3)
    }

    fun StackOverflowException() {
        System.err.println("$prefix: Stack Overflow Exception")
        exitProcess(4)
    }

    fun EmptyStackException() {
        System.err.println("$prefix: Empty Stack Exception")
        exitProcess(5)
    }

    fun ArithmeticException() {
        System.err.println("$prefix: Arithmetic Exception")
        exitProcess(6)
    }

    fun SystemCallException() {
        System.err.println("$prefix: System Call Exception")
        exitProcess(7)
    }

    fun FileAccessException() {
        System.err.println("$prefix: File Access Exception")
        exitProcess(8)
    }

    fun SocketException() {
        System.err.println("$prefix: Socket Exception")
        exitProcess(9)
    }

    fun MemoryAllocationException() {
        System.err.println("$prefix: Memory Allocation Exception")
        exitProcess(10)
    }

    fun InvalidInstructionArgumentException(message: String) {
        System.err.println("$prefix: Invalid Instruction Argument \"$message\"")
        exitProcess(11)
    }
}