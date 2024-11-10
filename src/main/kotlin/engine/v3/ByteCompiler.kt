package engine.v3

import data.registers.RegisterType
import java.io.*


val compilerElements = CompilerElements()
private const val filename = "w.mbin"
fun main() {
	val bc = ByteCompiler()
	val be = ByteExecutor()
	bc.writeStructuredData(filename)
	be.execute(File(filename))
}

open class CompilerBits {
	fun lit(outputStream: DataOutputStream, registerType: RegisterType, value: Long) {
		outputStream.writeInt(compilerElements.instructionCodes["lit"]!!)
		outputStream.writeInt(compilerElements.registerCodes[registerType]!!)
		outputStream.writeLong(value)
	}

	fun printr(outputStream: DataOutputStream, registerType: RegisterType) {
		outputStream.writeInt(compilerElements.instructionCodes["printr"]!!)
		outputStream.writeInt(compilerElements.registerCodes[registerType]!!)
	}
}


class ByteCompiler {
	val compilerBits = CompilerBits()
	fun writeStructuredData(filename: String) {

		DataOutputStream(FileOutputStream(filename)).use {
			compilerBits.lit(it, RegisterType.X1, 10)
			compilerBits.printr(it, RegisterType.X1)
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

