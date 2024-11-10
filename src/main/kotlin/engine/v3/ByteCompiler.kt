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
//	be.execute(File(filename))
	be.readStructuredData(filename)
}

open class CompilerBits(val outputStream: DataOutputStream) {
	fun lit(registerType: RegisterType, value: Long) {
		outputStream.writeByte(compilerElements.instructionCodes["lit"]!!)
		outputStream.writeByte(compilerElements.registerCodes[registerType]!!)
		outputStream.writeLong(value)
	}

	fun printr(registerType: RegisterType) {
		outputStream.writeByte(compilerElements.instructionCodes["printr"]!!)
		outputStream.writeByte(compilerElements.registerCodes[registerType]!!)
	}
}


class ByteCompiler {

	fun writeStructuredData(filename: String) {

		DataOutputStream(FileOutputStream(filename)).use {
			val compilerBits = CompilerBits(it)
			compilerBits.lit(RegisterType.X1, 10)
			compilerBits.printr(RegisterType.X1)
		}
	}
}

class ByteExecution {
	fun readStructuredData(filename: String) {
		DataInputStream(FileInputStream(filename)).use { inputStream ->

			for (i in 0 until 100) {
				println(inputStream.read())
			}


		}
	}
}

