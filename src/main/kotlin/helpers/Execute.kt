package org.example.helpers

import org.example.kvmInternals.instructions.Instruction
import java.io.File

class Execute {
    fun run(command: String) {}


    fun execute(file: File) {

    }

    fun parser(file: File) {
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
        out.forEach { println(it) }
    }
}