package engine.v3

import engine.TransMapIDs
import engine.execution.InstructData
import engine.parser
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream

class ByteCompiler {
	private val transMapIDs = TransMapIDs()

	fun execute(input: List<InstructData>, outputFile: File): Unit = try {
		DataOutputStream(FileOutputStream(outputFile)).use { dos -> // Use DataOutputStream for binary writing
			for (instruction in input) {
				when (instruction.name) {
					"comment", "emptyLine" -> {} // Skip comments and empty lines

					"add", "sub", "mul", "div", "mod", "mov", "and", "or", "xor", "shr", "shl", "strcmp", "strcat", "strcpy", "store", "lt", "gt" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeByte(transMapIDs.registers[instruction.values[0]]!!.code) // Write register 1
						dos.writeByte(transMapIDs.registers[instruction.values[1]]!!.code) // Write register 2
					}

					"jz", "jnz" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeShort(instruction.values[0] as Int)   // Write short value
						dos.writeByte(transMapIDs.registers[instruction.values[1]]!!.code) // Write register
					}

					"lit" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code)
						dos.writeByte(transMapIDs.registers[instruction.values[0]]!!.code)
						when (val literalValue = instruction.values[1] as Long) {
							in Byte.MIN_VALUE..Byte.MAX_VALUE -> {
								dos.writeByte(1) // 1-byte value
								dos.writeByte(literalValue.toInt())
							}

							in Short.MIN_VALUE..Short.MAX_VALUE -> {
								dos.writeByte(2) // 2-byte value
								dos.writeShort(literalValue.toInt())
							}

							in Int.MIN_VALUE..Int.MAX_VALUE -> {
								dos.writeByte(4) // 4-byte value
								dos.writeInt(literalValue.toInt())
							}

							else -> {
								dos.writeByte(8) // 8-byte value
								dos.writeLong(literalValue)
							}
						}
					}

					"jmp" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeShort(instruction.values[0] as Int)   // Write address as short
					}

					"printr", "push", "pop", "peek", "not", "strlen" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeByte(transMapIDs.registers[instruction.values[0]]!!.code) // Write register
					}

					"load" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeByte(transMapIDs.registers[instruction.values[0]]!!.code) // Write register 1
						dos.writeByte(transMapIDs.registers[instruction.values[1]]!!.code) // Write register 2

					}

					"str" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeByte(transMapIDs.registers[instruction.values[0]]!!.code) // Write register
						dos.writeUTF(instruction.values[1] as String) // Write string (UTF format)
					}

					"syscall" -> {
						dos.writeByte(transMapIDs.instructions[instruction.name]!!.code) // Write opcode
						dos.writeByte(transMapIDs.registers[instruction.values[0]]!!.code) // Write system call ID
					}

					else -> throw IllegalArgumentException("Unknown instruction: ${instruction.name}")
				}
			}
		}
	} catch (e: Exception) {
		System.err.println("Compilation failed: ${e.message}")
	}
}

fun main() {
	val parsed = parser(File("main.kar"))
	ByteCompiler().execute(parsed, File("main.mbin"))
	println("Compiled with 0 Issues!!!!!")
	ByteExecutor().execute(
		inputMarFile = File("main.mbin"),
	)
}