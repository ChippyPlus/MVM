package engine.execution

import data.registers.enumIdenifiers.SuperRegisterType
import debugger.DebugEngine
import engine.parser
import errors
import helpers.toSuperRegisterType
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
import internals.instructions.strings.*
import vm
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
    private fun run(command: List<InstructData>, usingDebugEngine: DebugEngine? = null) {
        while (true) {
            vm.pc++
            if (usingDebugEngine != null) {/* This is an optional thing and is checked over each iteration
 TODO fix the iteration performance oversight */
                usingDebugEngine.eachInteraction()
                usingDebugEngine.lineSpecific()
            }
            if (vm.pc - 1L == command.size.toLong()) {
                break
            }
            val args = command[vm.pc - 1].values
            when (command[vm.pc - 1].name) {
                "gt" -> vm.arithmetic.gt(
                    operand1 = args[1] as SuperRegisterType, operand2 = args[2] as SuperRegisterType
                )

                "lt" -> vm.arithmetic.lt(
                    operand1 = args[1] as SuperRegisterType, operand2 = args[2] as SuperRegisterType
                )

                "str" -> vm.strings.str(args[0].toString().toSuperRegisterType(), args[1].toString())
                "strcmp" -> vm.strings.strcmp(
                    string1 = args[1] as SuperRegisterType, string2 = args[2] as SuperRegisterType
                )

                "strcat" -> vm.strings.strcat(
                    string1 = args[1] as SuperRegisterType, string2 = args[2] as SuperRegisterType
                )

                "strcpy" -> vm.strings.strcpy(
                    source = args[1] as SuperRegisterType, destination = args[2] as SuperRegisterType
                )

                "cpy" -> vm.dataTransfer.cpy(
                    register1 = args[1] as SuperRegisterType, register2 = args[2] as SuperRegisterType
                )

                "add" -> vm.arithmetic.add(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "sub" -> vm.arithmetic.sub(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )


                "mul" -> vm.arithmetic.mul(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "div" -> vm.arithmetic.div(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "mod" -> vm.arithmetic.mod(
                    registerA = args[1] as SuperRegisterType, registerB = args[2] as SuperRegisterType
                )

                "eq" -> vm.arithmetic.eq(
                    operand1 = args[1] as SuperRegisterType, operand2 = args[2] as SuperRegisterType
                )

                "strlen" -> vm.strings.strlen(addressRegister = args[1] as SuperRegisterType)
                "lit" -> vm.dataTransfer.lit(source = args[0] as SuperRegisterType, value = args[1] as Long)
                "mov" -> vm.dataTransfer.mov(
                    source = args[0] as SuperRegisterType, destination = args[1] as SuperRegisterType
                )

                "jmp" -> vm.controlFlow.jmp(targetAddress = args[0] as Int - 1)
                "jz" -> vm.controlFlow.jz(
                    targetAddress = args[0] as Int - 1, testRegister = args[1] as SuperRegisterType
                )

                "jnz" -> vm.controlFlow.jnz(
                    targetAddress = args[0] as Int - 1, testRegister = args[1] as SuperRegisterType
                )

                "peek" -> vm.stackOperations.peek(destination = args[0] as SuperRegisterType)
                "pop" -> vm.stackOperations.pop(destination = args[0] as SuperRegisterType)
                "push" -> vm.stackOperations.push(registerType = args[0] as SuperRegisterType)
                "store" -> vm.memory.store(
                    source = args[0] as SuperRegisterType, destination = args[1] as SuperRegisterType
                )

                "load" -> vm.memory.load(
                    memoryAddress = args[0] as SuperRegisterType, destination = args[1] as SuperRegisterType
                )

                "shl" -> vm.bitwise.shl(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "shr" -> vm.bitwise.shr(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "and" -> vm.bitwise.and(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "not" -> vm.bitwise.not(operand = args[0] as SuperRegisterType)
                "or" -> vm.bitwise.or(operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType)
                "xor" -> vm.bitwise.xor(
                    operand1 = args[0] as SuperRegisterType, operand2 = args[1] as SuperRegisterType
                )

                "syscall" -> vm.systemCall.execute(
                    callId = args[0] as SuperRegisterType,
                    s2 = args[1] as SuperRegisterType,
                    s3 = args[2] as SuperRegisterType,
                    s4 = args[3] as SuperRegisterType
                )

                "prints" -> vm.ioAbstractions.prints()
                "printr" -> vm.ioAbstractions.printr(register = args[0] as SuperRegisterType)
                else -> errors.InvalidInstructionException(command[vm.pc - 1].name)
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