package data.registers

import data.registers.RegisterDataType.*

/**
 * Represents a supertype encompassing all register types in the virtual machine.
 *
 * This enumeration allows for a unified way to refer to any register type, regardless of its specific category (General, System, or Return).
 */
enum class RegisterType {
	G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, IF0, IF1, IF2, IF3, IF4, IF5, IF6, IF7, IF8, IF9, R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, S0, S1, S2, S3, I0, I1, I2, I3, I4, I5, I6, I7, I8, I9, X0, X1, X2, X3, X4, X5, X6, X7, X8, X9
}

fun String.toRegisterDataType() = when (this.lowercase()) {
	"byte" -> RByte
	"short" -> RShort
	"int" -> RInt
	"long" -> RLong
	"float" -> RFloat
	else -> null

}


enum class RegisterDataType {
	RByte, RShort, RInt, RLong, RFloat
}


data class RegisterData(var data: Number?, var dataType: RegisterDataType) {

	fun read(): Long? {
		return data?.toLong()
	}

	fun write(value: Long?) {
		if (value == null) {
			data = null
			return
		}

		data = when (dataType) {
			RByte -> value.toByte()
			RShort -> value.toShort()
			RInt -> value.toInt()
			RLong -> value.toLong()
			RFloat -> value.toFloat()
		}.toLong()
	}


	fun readFloat(): Float? {
		return data?.toFloat()
	}

	fun writeFloat(value: Float?) {
		if (value == null) {
			data = null
			return
		}

		data = when (dataType) {
			RByte -> value.toInt().toByte()
			RShort -> value.toInt().toShort()
			RInt -> value.toInt()
			RLong -> value.toLong()
			RFloat -> value.toFloat()
		}.toFloat()
	}

	fun settype(newType: RegisterDataType) {
		dataType = when (newType) {
			RByte -> RByte
			RShort -> RShort
			RInt -> RInt
			RLong -> RLong
			RFloat -> RFloat
		}

	}

}