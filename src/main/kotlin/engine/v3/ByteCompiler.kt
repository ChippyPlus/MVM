package engine.v3

import data.registers.RegisterType
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream


val compilerElements = CompilerElements()
private const val filename = "w.mbin"
fun main() {
	val bc = ByteCompiler()
	val be = ByteExecution()
	bc.writeStructuredData(filename)
	be.readStructuredData(filename)
}


class ByteCompiler {
	fun writeStructuredData(filename: String) {
		DataOutputStream(FileOutputStream(filename)).use { outputStream ->
			outputStream.writeInt(compilerElements.instructionCodes["lit"]!!)
			outputStream.writeInt(compilerElements.registerCodes[RegisterType.G1]!!)
			outputStream.writeLong(10)
			outputStream.writeInt(compilerElements.instructionCodes["printr"]!!)
			outputStream.writeInt(compilerElements.registerCodes[RegisterType.G1]!!)

		}
	}
}

class ByteExecution {
	fun readStructuredData(filename: String) {
		DataInputStream(FileInputStream(filename)).use { inputStream ->
			val opcode = inputStream.readInt()
			val register = RegisterType.entries[inputStream.readInt()]
			val data = inputStream.readLong()
			println("Instruction: ${compilerElements.instructionCodes.entries.find { it.value == opcode }!!.key}")
			println("Register: $register")
			println("Data: $data")

		}
	}
}