package engine.v3


import hertz
import vm
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.lang.Thread.sleep

class ByteExecutor {
	private val compilerElements = CompilerElements()

	fun execute(inputMarFile: File) {
		DataInputStream(FileInputStream(inputMarFile)).use { dis ->
			while (dis.available() > 0) {
				sleep(hertz)
				vm.pc++

				when (val opcode = dis.readByte().toInt()) {
					compilerElements.instructionCodes["lit"]!! -> { // LIT instruction
						val register = compilerElements.codesToRegisters[dis.readByte().toInt()]!!
						val value = dis.readLong()

						println("Lit $register, $value")
					}


					compilerElements.instructionCodes["printr"]!!,
						-> {
						val register = compilerElements.codesToRegisters[dis.readByte().toInt()]!!
						println("Printr $register")
					}



					else -> throw IllegalStateException("Invalid Opcode: $opcode") // Handle unknown opcodes
				}

			}
		}
		vm.pc = 0
	}
}
