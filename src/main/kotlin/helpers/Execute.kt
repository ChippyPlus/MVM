package org.example.helpers

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.kvm
import org.example.kvmInternals.instructions.Instruction
import org.example.kvmInternals.instructions.arithmetic.*
import org.example.kvmInternals.instructions.bitwise.*
import org.example.kvmInternals.instructions.controlFlow.jmp
import org.example.kvmInternals.instructions.controlFlow.jnz
import org.example.kvmInternals.instructions.controlFlow.jz

import org.example.kvmInternals.instructions.dataTransfer.lit
import org.example.kvmInternals.instructions.dataTransfer.mov
import org.example.kvmInternals.instructions.ioAbstractions.prints
import org.example.kvmInternals.instructions.memory.load
import org.example.kvmInternals.instructions.memory.store
import org.example.kvmInternals.instructions.stackOperations.peek
import org.example.kvmInternals.instructions.stackOperations.pop
import org.example.kvmInternals.instructions.stackOperations.push
import java.io.File

class Execute {
    /**
     * @param command please only give this argument from `org.example.helpers.Execute.parser`

     */
    private fun run(command: MutableList<Instruction>) {
        while (true) {
            kvm.pc++
            if (kvm.pc - 1 == command.size) {
                break
            }

            when (val instruction: Any = command[kvm.pc - 1]) {
                is Instruction.Lit -> kvm.dataTransfer.lit(instruction.destination, instruction.value)
                is Instruction.Mov -> kvm.dataTransfer.mov(instruction.source, instruction.destination)
                is Instruction.Add -> kvm.arithmetic.add(instruction.operand1, instruction.operand2)
                is Instruction.Sub -> kvm.arithmetic.sub(instruction.operand1, instruction.operand2)
                is Instruction.Mul -> kvm.arithmetic.mul(instruction.operand1, instruction.operand2)
                is Instruction.Div -> kvm.arithmetic.div(instruction.operand1, instruction.operand2)
                is Instruction.Mod -> kvm.arithmetic.mod(instruction.operand1, instruction.operand2)
                /**
                 * Minus one for the `controlFlow` functions are
                 * because the pc will be incremented after the instruction is executed,
                 * so we want to point to the instruction before the target address
                 */
                is Instruction.Jmp -> kvm.controlFlow.jmp(instruction.targetAddress - 1)
                is Instruction.Jz -> kvm.controlFlow.jz(instruction.targetAddress - 1, instruction.testRegister)
                is Instruction.Jnz -> kvm.controlFlow.jnz(instruction.targetAddress - 1, instruction.testRegister)

                is Instruction.Peek -> kvm.stackOperations.peek(instruction.destination)
                is Instruction.Pop -> kvm.stackOperations.pop(instruction.destination)
                is Instruction.Push -> kvm.stackOperations.push(instruction.source)
                is Instruction.Prints -> kvm.ioAbstractions.prints()

                is Instruction.Store -> kvm.memory.store(instruction.source, instruction.memoryAddress)
                is Instruction.Load -> kvm.memory.load(instruction.memoryAddress, instruction.destination)

                is Instruction.Shl -> kvm.bitwise.shl(instruction.operand, instruction.shiftAmount)
                is Instruction.Shr -> kvm.bitwise.shr(instruction.operand, instruction.shiftAmount)

                is Instruction.Eq -> kvm.arithmetic.eq(instruction.operand1, instruction.operand2)

                is Instruction.And -> kvm.bitwise.and(instruction.operand1, instruction.operand2)
                is Instruction.Not -> kvm.bitwise.not(instruction.operand)
                is Instruction.Or -> kvm.bitwise.or(instruction.operand1, instruction.operand2)
                is Instruction.Xor -> kvm.bitwise.xor(instruction.operand1, instruction.operand2)

                is Instruction.Syscall -> kvm.systemCall.execute(
                    instruction.systemCallNumber, instruction.argument1, instruction.argument2, instruction.argument3
                )
            }
        }
    }


    fun execute(file: File) {
        val tokens = parser(file)
        @Suppress("UNCHECKED_CAST") ((tokens as? MutableList<Instruction>)?.let { run(it) })
        // This may look strange but. It works for me
    }

    fun parser(file: File): MutableList<Any> {
        // map of data classes
        val out = emptyArray<Any>().toMutableList()
        val tokens = emptyList<MutableList<String>>().toMutableList()
        for (line in file.readLines()) {
            val _lineParts = emptyList<String>().toMutableList()
            for (token in line.split(' ')) {
                _lineParts.add(token)
            }
            tokens.add(_lineParts)
        }
        for (line in tokens) {
            when (val instruction = line[0]) {

                "SYSCALL" -> {
                    out.add(
                        Instruction.Syscall(
                            SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4
                        )
                    )
                }

                "" -> {// ignore empty line
                }


                "MOD" -> {
                    /** MOD G1 G2*/
                    out.add(Instruction.Mod(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "EQ" -> {
                    /** EQ G1 G2*/
                    out.add(Instruction.Eq(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "SHL" -> {
                    /** SHL G1 G2 */
                    out.add(Instruction.Shl(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "SHR" -> {
                    /** SHR G1 G2 */
                    out.add(Instruction.Shr(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "PEEK" -> {
                    /** PEEK R1 */
                    out.add(Instruction.Peek(line[1].toSuperRegisterType()))
                }

                "POP" -> {
                    /** POP R1 */
                    out.add(Instruction.Pop(line[1].toSuperRegisterType()))
                }

                "PUSH" -> {
                    /** PUSH R1 */
                    out.add(Instruction.Push(line[1].toSuperRegisterType()))
                }

                "PRINTS" -> {
                    /** PRINTS */
                    out.add(Instruction.Prints())
                }

                "DIV" -> {
                    /** DIV G1 G2 */
                    out.add(Instruction.Div(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }


                "AND" -> {
                    /** AND G1 G2 */
                    out.add(Instruction.And(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "OR" -> {
                    /** OR G1 G2 */
                    out.add(Instruction.Or(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "XOR" -> {
                    /** XOR G1 G2 */
                    out.add(Instruction.Xor(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "NOT" -> {
                    /** NOT G1 G2 */
                    out.add(Instruction.Not(line[1].toSuperRegisterType()))
                }


                "STORE" -> {
                    /** STORE G1 10 */
                    out.add(Instruction.Store(line[1].toSuperRegisterType(), line[2].toMemoryAddress()))
                }

                "LOAD" -> {
                    /** LOAD 10 R1 */
                    out.add(Instruction.Load(line[1].toMemoryAddress(), line[2].toSuperRegisterType()))
                }


                "LIT" -> {
                    /** LIT G1 10 */
                    out.add(Instruction.Lit(line[1].toSuperRegisterType(), line[2].toInt()))
                }

                "JMP" -> {
                    /** JMP 4 */
                    out.add(Instruction.Jmp(line[1].toInt()))
                }

                "JZ" -> {
                    /** JZ 4 R1 */
                    out.add(Instruction.Jz(line[1].toInt(), line[2].toSuperRegisterType()))
                }

                "JNZ" -> {
                    /** JNZ 4 R1 */
                    out.add(Instruction.Jnz(line[1].toInt(), line[2].toSuperRegisterType()))
                }

                "MOV" -> {
                    /** MOV G3 S4 */
                    out.add(Instruction.Mov(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "ADD" -> {
                    out.add(Instruction.Add(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "SUB" -> {
                    out.add(Instruction.Sub(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "MUL" -> {
                    out.add(Instruction.Mul(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                else -> {
                    errors.InvalidInstructionException(instruction)
                }


            }
        }
        return out
    }
}