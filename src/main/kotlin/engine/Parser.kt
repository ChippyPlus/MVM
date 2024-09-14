package org.example.engine

import internals.instructions.Instruction
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.toMemoryAddress
import org.example.helpers.toSuperRegisterType
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
                out.add(Instruction.Store(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            }

            "LOAD" -> {
                /** LOAD 10 R1 */
                out.add(Instruction.Load(line[1].toMemoryAddress(), line[2].toSuperRegisterType()))
            }


            "LIT" -> {
                /** LIT G1 10 */
                out.add(Instruction.Lit(line[1].toSuperRegisterType(), line[2].toLong()))
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