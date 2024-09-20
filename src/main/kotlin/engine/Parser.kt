package engine

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.toFloatingRegisterType
import errors
import helpers.toMemoryAddress
import helpers.toSuperRegisterType
import internals.instructions.Instruction
import java.io.File

/**
 * Parses assembly code from a file and converts it into a list of instructions.
 *
 * @param file The file containing the assembly code.
 * @return A mutable list of [Instruction] objects representing the parsed instructions.
 * @throws InvalidInstructionException If an invalid instruction mnemonic is encountered.
 */
fun parser(file: File): MutableList<Any> {
    val out = emptyArray<Any>().toMutableList()
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
        // The first token is the instruction mnemonic
        when (val instruction = line[0]) {


            "FADD" -> out.add(
                Instruction.FAdd(
                    line[1].toSuperRegisterType().toFloatingRegisterType(),
                    line[2].toSuperRegisterType().toFloatingRegisterType(),
                )
            )

            "FSUB" -> out.add(
                Instruction.FSub(
                    line[1].toSuperRegisterType().toFloatingRegisterType(),
                    line[2].toSuperRegisterType().toFloatingRegisterType(),
                )
            )

            "FMUL" -> out.add(
                Instruction.FMul(
                    line[1].toSuperRegisterType().toFloatingRegisterType(),
                    line[2].toSuperRegisterType().toFloatingRegisterType(),
                )
            )

            "FDIV" -> out.add(
                Instruction.FDiv(
                    line[1].toSuperRegisterType().toFloatingRegisterType(),
                    line[2].toSuperRegisterType().toFloatingRegisterType(),
                )
            )

            "FMOD" -> out.add(
                Instruction.FMod(
                    line[1].toSuperRegisterType().toFloatingRegisterType(),
                    line[2].toSuperRegisterType().toFloatingRegisterType(),
                )
            )

            "FEQ" -> out.add(
                Instruction.FEq(
                    line[1].toSuperRegisterType().toFloatingRegisterType(),
                    line[2].toSuperRegisterType().toFloatingRegisterType(),
                )
            )

            "FLIT" -> out.add(
                Instruction.FLit(
                    line[1].toSuperRegisterType().toFloatingRegisterType(), line[2].toDouble()
                )
            )

            "ITOF" -> out.add(
                Instruction.Itof(
                    line[1].toSuperRegisterType(), line[2].toSuperRegisterType().toFloatingRegisterType()
                )
            )


            "STRCPY" -> out.add(Instruction.StrCpy(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "STRCMP" -> out.add(Instruction.StrCmp(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "STRCAT" -> out.add(Instruction.StrCat(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "SUBSTR" -> out.add(
                Instruction.SubStr(
                    line[1].toSuperRegisterType(),
                    line[2].toSuperRegisterType(),
                    line[3].toSuperRegisterType(),
                )
            )

            "FIND" -> out.add(Instruction.Find(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "CPY" -> out.add(Instruction.Cpy(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "STRLEN" -> out.add(Instruction.Strlen(line[1].toSuperRegisterType()))
            "PRINTR" -> out.add(Instruction.Printr(line[1].toSuperRegisterType()))
            "STR" -> out.add(Instruction.Str(line[1].toSuperRegisterType(), line.joinToString(" ").split("\"")[1]))
            "SYSCALL" -> out.add(
                Instruction.Syscall(
                    SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4
                )
            )

            "" -> { /* Ignore empty lines */
            }

            "//" -> { /* Ignore comments */
            }

            "MOD" -> out.add(Instruction.Mod(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "EQ" -> out.add(Instruction.Eq(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "SHL" -> out.add(Instruction.Shl(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "SHR" -> out.add(Instruction.Shr(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "PEEK" -> out.add(Instruction.Peek(line[1].toSuperRegisterType()))
            "POP" -> out.add(Instruction.Pop(line[1].toSuperRegisterType()))
            "PUSH" -> out.add(Instruction.Push(line[1].toSuperRegisterType()))
            "PRINTS" -> out.add(Instruction.Prints())
            "DIV" -> out.add(Instruction.Div(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "AND" -> out.add(Instruction.And(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "OR" -> out.add(Instruction.Or(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "XOR" -> out.add(Instruction.Xor(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "NOT" -> out.add(Instruction.Not(line[1].toSuperRegisterType()))
            "STORE" -> out.add(Instruction.Store(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "LOAD" -> out.add(Instruction.Load(line[1].toMemoryAddress(), line[2].toSuperRegisterType()))
            "LIT" -> out.add(Instruction.Lit(line[1].toSuperRegisterType(), line[2].toLong()))
            "JMP" -> out.add(Instruction.Jmp(line[1].toInt()))
            "JZ" -> out.add(Instruction.Jz(line[1].toInt(), line[2].toSuperRegisterType()))
            "JNZ" -> out.add(Instruction.Jnz(line[1].toInt(), line[2].toSuperRegisterType()))
            "MOV" -> out.add(Instruction.Mov(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "ADD" -> out.add(Instruction.Add(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "SUB" -> out.add(Instruction.Sub(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            "MUL" -> out.add(Instruction.Mul(line[1].toSuperRegisterType(), line[2].toSuperRegisterType()))
            else -> errors.InvalidInstructionException(instruction) // Throw an exception for invalid instructions
        }
    }
    return out
}