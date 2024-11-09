package engine.v3

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
			outputStream.writeInt(12345)
			outputStream.writeFloat(3.14f)
			outputStream.writeBoolean(true)
			outputStream.writeUTF("Hello, binary world!")
		}
	}
}

class ByteExecution {
	fun readStructuredData(filename: String) {
		DataInputStream(FileInputStream(filename)).use { inputStream ->
			val intValue = inputStream.readInt()
			val floatValue = inputStream.readFloat()
			val booleanValue = inputStream.readBoolean()
			val stringValue = inputStream.readUTF()

			println("Int: $intValue")
			println("Float: $floatValue")
			println("Boolean: $booleanValue")
			println("String: $stringValue")
		}
	}
}
