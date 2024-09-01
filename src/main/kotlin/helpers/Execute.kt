package org.example.helpers

import org.example.kvm
import org.example.kvmInternals.instructions.Instruction
import org.example.kvmInternals.instructions.dataTransfer.lit
import java.io.File

class Execute {

    /**
     * @param command please only give this argument from `org.example.helpers.Execute.parser`

     */
    fun run(command: MutableList<Any>) {
        for (instruction in command) {
            when (instruction) {
                is Instruction.Lit -> {
                    kvm.dataTransfer.lit(instruction.destination,instruction.value)
                }
            }
        }
    }


    fun execute(file: File) {

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
            }
        }
//        out.forEach { println(it) }
        return out
    }
}