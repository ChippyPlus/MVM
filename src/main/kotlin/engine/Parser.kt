package engine

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.InstructData
import errors
import helpers.toSuperRegisterType
import vm
import java.io.File

/**
 * Parses assembly code from a file and converts it into a list of instructions.
 *
 * @param file The file containing the assembly code.
 * @return A mutable list of [Instruction] objects representing the parsed instructions.
 * @throws InvalidInstructionException If an invalid instruction mnemonic is encountered.
 */
fun parser(file: File): List<InstructData> {
    val out = emptyArray<InstructData>().toMutableList()
    val tokens = emptyList<MutableList<String>>().toMutableList()

    // Read each line from the file and split it into tokens
    for (line in file.readLines()) {
        val _lineParts = emptyList<String>().toMutableList()
        for (token in line.split(' ')) {
            _lineParts.add(token)
        }
        tokens.add(_lineParts)
    }

    for (line in tokens) {
        vm.pc++
        val instruction = line[0].uppercase()
        println(instruction)
        when (instruction) {

            "STRCPY" -> out.add(
                InstructData(
                    name = "strcpy", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "STRCMP" -> out.add(
                InstructData(
                    name = "strcmp", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "STRCAT" -> out.add(
                InstructData(
                    name = "strcat", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "SUBSTR" -> out.add(
                InstructData(
                    name = "substr",
                    arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType(), line[3].toSuperRegisterType())
                )
            )

            "FIND" -> out.add(
                InstructData(
                    name = "find", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "CPY" -> out.add(
                InstructData(
                    name = "cpy", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "STRLEN" -> out.add(
                InstructData(
                    name = "strlen", arrayOf(line[1].toSuperRegisterType())
                )
            )

            "PRINTR" -> out.add(
                InstructData(
                    name = "printr", arrayOf(line[1].toSuperRegisterType())
                )
            )

            "STR" -> out.add(
                InstructData(
                    name = "str", arrayOf(line[1].toSuperRegisterType(), line.joinToString(" ").split("\"")[1])
                )
            )

            "SYSCALL" -> out.add(
                InstructData(
                    name = "syscall",
                    arrayOf(SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4)
                )
            )

            "" -> { /* Ignore empty lines */
            }

            "//" -> { /* Ignore comments */
            }

            "MOD" -> out.add(
                InstructData(
                    name = "mod", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "EQ" -> out.add(
                InstructData(
                    name = "eq", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "SHL" -> out.add(
                InstructData(
                    name = "shl", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "SHR" -> out.add(
                InstructData(
                    name = "shr", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "PEEK" -> out.add(
                InstructData(
                    name = "peek", arrayOf(line[1].toSuperRegisterType())
                )
            )

            "POP" -> out.add(
                InstructData(
                    name = "pop", arrayOf(line[1].toSuperRegisterType())
                )
            )

            "PUSH" -> out.add(
                InstructData(
                    name = "push", arrayOf(line[1].toSuperRegisterType())
                )
            )

            "PRINTS" -> out.add(
                InstructData(
                    name = "prints", emptyArray()
                )
            )

            "DIV" -> out.add(
                InstructData(
                    name = "div", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "AND" -> out.add(
                InstructData(
                    name = "and", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "OR" -> out.add(
                InstructData(
                    name = "or", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "XOR" -> out.add(
                InstructData(
                    name = "xor", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "NOT" -> out.add(
                InstructData(
                    name = "not", arrayOf(line[1].toSuperRegisterType())
                )
            )

            "STORE" -> out.add(
                InstructData(
                    name = "store", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "LOAD" -> out.add(
                InstructData(
                    name = "load", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "LIT" -> out.add(
                InstructData(
                    name = "lit", arrayOf(line[1].toSuperRegisterType(), line[2].toLong())
                )
            )

            "JMP" -> out.add(
                InstructData(
                    name = "jmp", arrayOf(line[1].toInt())
                )
            )

            "JZ" -> out.add(
                InstructData(
                    name = "jz", arrayOf(line[1].toInt(), line[2].toSuperRegisterType())
                )
            )

            "JNZ" -> out.add(
                InstructData(
                    name = "jnz", arrayOf(line[1].toInt(), line[2].toSuperRegisterType())
                )
            )

            "MOV" -> out.add(
                InstructData(
                    name = "mov", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "ADD" -> out.add(
                InstructData(
                    name = "add", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "SUB" -> out.add(
                InstructData(
                    name = "sub", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            "MUL" -> out.add(
                InstructData(
                    name = "mul", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
                )
            )

            else -> errors.InvalidInstructionException(instruction) // Throw an exception for invalid instructions
        }
    }
    vm.pc = 0
    return out
}