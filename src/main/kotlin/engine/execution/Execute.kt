package engine.execution

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import debugger.DebugEngine
import engine.parser
import errors
import internals.instructions.arithmetic.*
import internals.instructions.bitwise.*
import internals.instructions.controlFlow.jmp
import internals.instructions.controlFlow.jnz
import internals.instructions.controlFlow.jz
import internals.instructions.dataTransfer.cpy
import internals.instructions.dataTransfer.lit
import internals.instructions.dataTransfer.mov
import internals.instructions.ioAbstractions.printr
import internals.instructions.ioAbstractions.prints
import internals.instructions.memory.load
import internals.instructions.memory.store
import internals.instructions.stackOperations.peek
import internals.instructions.stackOperations.pop
import internals.instructions.stackOperations.push
import internals.instructions.strings.strcat
import internals.instructions.strings.strcmp
import internals.instructions.strings.strcpy
import internals.instructions.strings.strlen
import kvm
import java.io.File


/**
 * The class responsible for executing parsed instructions.
 */
class Execute {

    /**
     * Executes a list of parsed [Instruction] objects.
     *
     * This function represents the main execution loop of the virtual machine.
     * It iterates through the instructions, executes them one by one, and handles control flow.
     *
     * @param command The list of instructions to execute.
     */
    private fun run(command: MutableList<InstructData>, usingDebugEngine: DebugEngine? = null) {
        while (true) {
            kvm.pc++
            if (usingDebugEngine != null) {/* This is an optional thing and is checked over each iteration
 TODO fix the iteration performance oversight */
                usingDebugEngine.eachInteraction()
                usingDebugEngine.lineSpecific()
            }
            if (kvm.pc - 1L == command.size.toLong()) {
                break
            }
            val args = command[kvm.pc - 1].values
            when (command[kvm.pc - 1].name) {
                "strcmp" -> kvm.strings.strcmp(
                    string1 = args[1] as SuperRegisterType, string2 = args[2] as SuperRegisterType
                )

                "strcat" -> kvm.strings.strcat(
                    string1 = args[1] as SuperRegisterType, string2 = args[2] as SuperRegisterType
                )

                "strcpy" -> kvm.strings.strcpy(
                    source = args[1] as SuperRegisterType, destination = args[2] as SuperRegisterType
                )

                "cpy" -> kvm.dataTransfer.cpy(
                    register1 = args[1] as SuperRegisterType, register2 = args[2] as SuperRegisterType
                )

                "add" -> kvm.arithmetic.add(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "sub" -> kvm.arithmetic.sub(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )


                "mul" -> kvm.arithmetic.mul(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "div" -> kvm.arithmetic.div(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "mod" -> kvm.arithmetic.mod(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "eq" -> kvm.arithmetic.eq(
                    operand1 = args[1] as SuperRegisterType, operand2 = args[2] as SuperRegisterType
                )

                "strlen" -> kvm.strings.strlen(addressRegister = args[1] as SuperRegisterType)
                "lit" -> kvm.dataTransfer.lit(Source = args[0] as SuperRegisterType, value = args[1] as Long)
                "mov" -> kvm.dataTransfer.mov(
                    Source = args[0] as SuperRegisterType, Destination = args[1] as SuperRegisterType
                )

                "jmp" -> kvm.controlFlow.jmp(targetAddress = args[0] as Int - 1)
                "jz" -> kvm.controlFlow.jz(
                    targetAddress = args[0] as Int - 1, testRegister = args[1] as SuperRegisterType
                )

                "jnz" -> kvm.controlFlow.jnz(
                    targetAddress = args[0] as Int - 1, testRegister = args[1] as SuperRegisterType
                )

                "peek" -> kvm.stackOperations.peek(destination = args[0] as SuperRegisterType)
                "pop" -> kvm.stackOperations.pop(destination = args[0] as SuperRegisterType)
                "push" -> kvm.stackOperations.push(registerType = args[0] as SuperRegisterType)
                "store" -> kvm.memory.store(
                    source = args[0] as SuperRegisterType, destination = args[1] as SuperRegisterType
                )

                "load" -> kvm.memory.load(
                    memoryAddress = args[0] as MemoryAddress, destination = args[1] as SuperRegisterType
                )

                "shl" -> kvm.bitwise.shl(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "shr" -> kvm.bitwise.shr(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "and" -> kvm.bitwise.and(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "not" -> kvm.bitwise.not(operand = args[0] as SuperRegisterType)
                "or" -> kvm.bitwise.or(operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType)
                "xor" -> kvm.bitwise.xor(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "syscall" -> kvm.systemCall.execute(
                    callId = args[0] as SuperRegisterType,
                    s2 = args[1] as SuperRegisterType,
                    s3 = args[2] as SuperRegisterType,
                    s4 = args[3] as SuperRegisterType
                )

                "prints" -> kvm.ioAbstractions.prints()
                "printr" -> kvm.ioAbstractions.printr(register = args[0] as SuperRegisterType)
                else -> errors.InvalidInstructionException(command[kvm.pc - 1].name)
            }

        }
    }


    /**
     * Parses and executes the instructions from the specified file.
     *
     * @param file The file containing the assembly code to execute.
     */
    fun execute(file: File, usingDebugTools: DebugEngine? = null) {
        val tokens = parser(file)
        this@Execute.run(command = tokens, usingDebugEngine = usingDebugTools)
    }
}