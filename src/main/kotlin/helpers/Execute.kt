package org.example.helpers

import org.example.kvm
import org.example.kvmInternals.instructions.Instruction
import org.example.kvmInternals.instructions.arithmetic.add
import org.example.kvmInternals.instructions.arithmetic.div
import org.example.kvmInternals.instructions.arithmetic.mul
import org.example.kvmInternals.instructions.arithmetic.sub

import org.example.kvmInternals.instructions.dataTransfer.lit
import org.example.kvmInternals.instructions.dataTransfer.mov
import org.example.kvmInternals.instructions.stackOperations.peek
import org.example.kvmInternals.instructions.stackOperations.pop
import org.example.kvmInternals.instructions.stackOperations.push
import java.io.File

class Execute {

    /**
     * @param command please only give this argument from `org.example.helpers.Execute.parser`

     */
    private fun run(command: MutableList<Any>) {
        var jumpBuffer = 0
        for ((index, instruction) in command.withIndex()) {
            when (instruction) {
                is Instruction.Lit -> kvm.dataTransfer.lit(instruction.destination, instruction.value)
                is Instruction.Mov -> kvm.dataTransfer.mov(instruction.source, instruction.destination)
                is Instruction.Add -> kvm.arithmetic.add(instruction.operand1, instruction.operand2)
                is Instruction.Sub -> kvm.arithmetic.sub(instruction.operand1, instruction.operand2)
                is Instruction.Mul -> kvm.arithmetic.mul(instruction.operand1, instruction.operand2)
                is Instruction.Div -> kvm.arithmetic.div(instruction.operand1, instruction.operand2)
                is Instruction.Jmp -> { TODO("SO what your doing is trying to find out how the jump function must work") }
                is Instruction.Peek -> kvm.stackOperations.peek(instruction.destination)
                is Instruction.Pop -> kvm.stackOperations.pop(instruction.destination)
                is Instruction.Push -> kvm.stackOperations.push(instruction.source)

                else -> error("Unknown instruction type ${instruction::class}!!!!!")
            }
        }
    }


    fun execute(file: File) {
        val tokens = parser(file)
        run(tokens)

    }

    private fun parser(file: File): MutableList<Any> {
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
            val instruction = line[0]
            when (instruction) {
                "LIT" -> {
                    // LIT G1 10
                    out.add(Instruction.Lit(line[1].toSuperRegisterType(), line[2].toInt()))
                }

                "JMP" -> {
                    // JMP 4
                    out.add(Instruction.Jmp(line[1].toInt()))
                }

                "MOV" -> {
                    // MOV G3 S4
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
                "DIV" -> {
                    out.add(Instruction.Div(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
                }

                "PEEK" -> {
                    out.add(Instruction.Peek(line[1].toSuperRegisterType()))
                }

                "POP" -> {
                    out.add(Instruction.Pop(line[1].toSuperRegisterType()))
                }

                "PUSH" -> {
                    out.add(Instruction.Push(line[1].toSuperRegisterType()))
                }
            }
        }
//        out.forEach { println(it) }
        return out
    }
}