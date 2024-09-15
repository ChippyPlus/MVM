package engine.execution

import internals.instructions.Instruction
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
import internals.instructions.strings.str
import internals.instructions.strings.strcat
import internals.instructions.strings.strcmp
import internals.instructions.strings.strcpy
import org.example.debugEngine
import org.example.engine.parser
import org.example.kvm
import internals.instructions.stackOperations.push
import internals.instructions.strings.strlen
import java.io.File

class Execute {
    /**
     * @param command please only give this argument from `org.example.Engine.Execute.parser`

     */
    private fun run(command: MutableList<Instruction>) {
        while (true) {
            kvm.pc++
            debugEngine.eachInteraction()
            debugEngine.lineSpecific()
            if (kvm.pc - 1L == command.size.toLong()) {
                break
            }
            when (val instruction: Any = command[kvm.pc - 1]) {
                is Instruction.StrCmp -> kvm.strings.strcmp(instruction.string1,instruction.string2)
                is Instruction.StrCat -> kvm.strings.strcat(instruction.string1, instruction.string2)
                is Instruction.StrCpy -> kvm.strings.strcpy(instruction.source, instruction.destination)
                is Instruction.Cpy -> kvm.dataTransfer.cpy(instruction.register1, instruction.register2)
                is Instruction.Add -> kvm.arithmetic.add(instruction.operand1, instruction.operand2)
                is Instruction.Sub -> kvm.arithmetic.sub(instruction.operand1, instruction.operand2)
                is Instruction.Mul -> kvm.arithmetic.mul(instruction.operand1, instruction.operand2)
                is Instruction.Div -> kvm.arithmetic.div(instruction.operand1, instruction.operand2)
                is Instruction.Mod -> kvm.arithmetic.mod(instruction.operand1, instruction.operand2)
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

    fun execute(file: File) {
        val tokens = parser(file)
        @Suppress("UNCHECKED_CAST") ((tokens as? MutableList<Instruction>)?.let { run(it) })
        // This may look strange but. It works for me
    }


}