package org.example.engine.execution

import org.example.debugEngine
import org.example.engine.parser
import org.example.kvm
import org.example.kvmInternals.instructions.Instruction
import org.example.kvmInternals.instructions.arithmetic.*
import org.example.kvmInternals.instructions.bitwise.*
import org.example.kvmInternals.instructions.controlFlow.jmp
import org.example.kvmInternals.instructions.controlFlow.jnz
import org.example.kvmInternals.instructions.controlFlow.jz
import org.example.kvmInternals.instructions.dataTransfer.lit
import org.example.kvmInternals.instructions.dataTransfer.mov
import org.example.kvmInternals.instructions.ioAbstractions.printr
import org.example.kvmInternals.instructions.ioAbstractions.prints
import org.example.kvmInternals.instructions.memory.load
import org.example.kvmInternals.instructions.memory.store
import org.example.kvmInternals.instructions.stackOperations.peek
import org.example.kvmInternals.instructions.stackOperations.pop
import org.example.kvmInternals.instructions.stackOperations.push
import org.example.kvmInternals.instructions.strings.str
import org.example.kvmInternals.instructions.strings.strlen
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
            if (kvm.pc - 1 == command.size) {
                break
            }
            when (val instruction: Any = command[kvm.pc - 1]) {
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