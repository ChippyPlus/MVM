package engine.execution

import debugger.DebugEngine
import engine.parser
import internals.instructions.Instruction
import internals.instructions.arithmetic.*
import internals.instructions.bitwise.*
import internals.instructions.controlFlow.jmp
import internals.instructions.controlFlow.jnz
import internals.instructions.controlFlow.jz
import internals.instructions.dataTransfer.cpy
import internals.instructions.dataTransfer.lit
import internals.instructions.dataTransfer.mov
import internals.instructions.floats.arithmetic.*
import internals.instructions.floats.dataTransfer.flit
import internals.instructions.floats.dataTransfer.itof
import internals.instructions.ioAbstractions.printr
import internals.instructions.ioAbstractions.prints
import internals.instructions.memory.load
import internals.instructions.memory.store
import internals.instructions.stackOperations.peek
import internals.instructions.stackOperations.pop
import internals.instructions.stackOperations.push
import internals.instructions.strings.*
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
    private fun run(command: MutableList<Instruction>, usingDebugEngine: DebugEngine? = null) {
        while (true) {
            kvm.pc++
            if (usingDebugEngine != null) {
                println("USING DEBUG")
                usingDebugEngine.eachInteraction()
                usingDebugEngine.lineSpecific()
            }
            if (kvm.pc - 1L == command.size.toLong()) {
                break
            }
            when (val instruction: Any = command[kvm.pc - 1]) {
                is Instruction.FAdd -> kvm.floats.fAdd(instruction.registerA, instruction.registerB)
                is Instruction.FSub -> kvm.floats.fSub(instruction.registerA, instruction.registerB)
                is Instruction.FMul -> kvm.floats.fMul(instruction.registerA, instruction.registerB)
                is Instruction.FDiv -> kvm.floats.fDiv(instruction.registerA, instruction.registerB)
                is Instruction.FEq -> kvm.floats.fEq(instruction.registerA, instruction.registerB)
                is Instruction.Itof -> kvm.floats.itof(instruction.destination, instruction.value)
                is Instruction.FLit -> kvm.floats.flit(instruction.source, instruction.value)
                is Instruction.FMod -> kvm.floats.fMod(instruction.registerA,instruction.registerB)


                is Instruction.StrCmp -> kvm.strings.strcmp(instruction.string1, instruction.string2)
                is Instruction.StrCat -> kvm.strings.strcat(instruction.string1, instruction.string2)
                is Instruction.StrCpy -> kvm.strings.strcpy(instruction.source, instruction.destination)
                is Instruction.Cpy -> kvm.dataTransfer.cpy(instruction.register1, instruction.register2)
                is Instruction.Add -> kvm.arithmetic.add(
                    instruction.operand1,
                    instruction.operand2
                )

                is Instruction.Sub -> kvm.arithmetic.sub(instruction.operand1, instruction.operand2)
                is Instruction.Mul -> kvm.arithmetic.mul(instruction.operand1, instruction.operand2)
                is Instruction.Div -> kvm.arithmetic.div(instruction.operand1, instruction.operand2)
                is Instruction.Mod -> kvm.arithmetic.mod(
                    instruction.operand1,
                    instruction.operand2
                )

                is Instruction.Eq -> kvm.arithmetic.eq(instruction.operand1, instruction.operand2)
                is Instruction.Strlen -> kvm.strings.strlen(instruction.addressRegister)
                is Instruction.Str -> kvm.strings.str(instruction.targetAddress, instruction.string)
                is Instruction.Lit -> kvm.dataTransfer.lit(instruction.destination, instruction.value)
                is Instruction.Mov -> kvm.dataTransfer.mov(instruction.source, instruction.destination)
                is Instruction.Jmp -> kvm.controlFlow.jmp(instruction.targetAddress - 1)
                is Instruction.Jz -> kvm.controlFlow.jz(instruction.targetAddress - 1, instruction.testRegister)
                is Instruction.Jnz -> kvm.controlFlow.jnz(instruction.targetAddress - 1, instruction.testRegister)
                is Instruction.Peek -> kvm.stackOperations.peek(instruction.destination)
                is Instruction.Pop -> kvm.stackOperations.pop(instruction.destination)
                is Instruction.Push -> kvm.stackOperations.push(instruction.source)
                is Instruction.Store -> kvm.memory.store(instruction.source, instruction.memoryAddress)
                is Instruction.Load -> kvm.memory.load(instruction.memoryAddress, instruction.destination)
                is Instruction.Shl -> kvm.bitwise.shl(instruction.operand, instruction.shiftAmount)
                is Instruction.Shr -> kvm.bitwise.shr(instruction.operand, instruction.shiftAmount)
                is Instruction.And -> kvm.bitwise.and(instruction.operand1, instruction.operand2)
                is Instruction.Not -> kvm.bitwise.not(instruction.operand)
                is Instruction.Or -> kvm.bitwise.or(instruction.operand1, instruction.operand2)
                is Instruction.Xor -> kvm.bitwise.xor(instruction.operand1, instruction.operand2)
                is Instruction.Syscall -> kvm.systemCall.execute(
                    instruction.systemCallNumber, instruction.argument1, instruction.argument2, instruction.argument3
                )

                is Instruction.Prints -> kvm.ioAbstractions.prints()
                is Instruction.Printr -> kvm.ioAbstractions.printr(instruction.register)
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
        @Suppress("UNCHECKED_CAST") ((tokens as? MutableList<Instruction>)?.let {
            run(
                it, usingDebugEngine = usingDebugTools
            )
        })
        // This may look strange, but it's safe!
    }
}