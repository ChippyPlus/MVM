package engine

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.toSuperRegisterType
import internals.instructions.Instruction
import helpers.toMemoryAddress
import java.io.File

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


            "STRCPY" -> {
                out.add(Instruction.StrCpy(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "STRCMP" -> {
                out.add(Instruction.StrCmp(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "STRCAT" -> {
                out.add(
                    Instruction.StrCat(
                        line[1].toSuperRegisterType(),
                        line[2].toSuperRegisterType(),
                    )
                )
            }

            "SUBSTR" -> {
                out.add(
                    Instruction.SubStr(
                        line[1].toSuperRegisterType(),
                        line[2].toSuperRegisterType(),
                        line[3].toSuperRegisterType(),
                    )
                )
            }

            "FIND" -> {
                out.add(
                    Instruction.Find(
                        line[1].toSuperRegisterType(), line[2].toSuperRegisterType()
                    )
                )
            }

            "CPY" -> out.add(Instruction.Cpy(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))

            "STRLEN" -> out.add(Instruction.Strlen(line[1].toSuperRegisterType()))

            "PRINTR" -> out.add(Instruction.Printr(line[1].toSuperRegisterType()))

            "STR" -> out.add(Instruction.Str(line[1].toSuperRegisterType(), line.joinToString(" ").split("\"")[1]))

            "SYSCALL" -> {
                out.add(
                    Instruction.Syscall(
                        SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4
                    )
                )
            }

            "" -> {// ignore empty line
            }

            "//" -> {// ignore comments

            }


            "MOD" -> {
                out.add(Instruction.Mod(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "EQ" -> {
                out.add(Instruction.Eq(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "SHL" -> {
                out.add(Instruction.Shl(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "SHR" -> {
                out.add(Instruction.Shr(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
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

            "PRINTS" -> {
                out.add(Instruction.Prints())
            }

            "DIV" -> {
                out.add(Instruction.Div(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }


            "AND" -> {
                out.add(Instruction.And(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "OR" -> {
                out.add(Instruction.Or(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "XOR" -> {
                out.add(Instruction.Xor(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "NOT" -> {
                out.add(Instruction.Not(line[1].toSuperRegisterType()))
            }


            "STORE" -> {
                out.add(Instruction.Store(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "LOAD" -> {
                out.add(Instruction.Load(line[1].toMemoryAddress(), line[2].toSuperRegisterType()))
            }


            "LIT" -> {
                out.add(Instruction.Lit(line[1].toSuperRegisterType(), line[2].toLong()))
            }

            "JMP" -> {
                out.add(Instruction.Jmp(line[1].toInt()))
            }

            "JZ" -> {
                out.add(Instruction.Jz(line[1].toInt(), line[2].toSuperRegisterType()))
            }

            "JNZ" -> {
                out.add(Instruction.Jnz(line[1].toInt(), line[2].toSuperRegisterType()))
            }

            "MOV" -> {
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