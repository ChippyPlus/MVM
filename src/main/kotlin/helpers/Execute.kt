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
        for (line  in file.readLines()) {
            val _lineParts = emptyList<String>().toMutableList()
            for (token in line.split(' ')) {
                _lineParts.add(token)
            }
            tokens.add(_lineParts)
        }
        println(tokens.joinToString(", "))
    }
}