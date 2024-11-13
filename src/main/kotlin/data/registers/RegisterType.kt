package data.registers

import data.registers.RegisterDataType.*
import internals.Vm

/**
 * Represents a supertype encompassing all register types in the virtual machine.
 *
 * This enumeration allows for a unified way to refer to any register type, regardless of its specific category (General, System, or Return).
 */
enum class RegisterType {
	G1, G2, G3, G4, G5, G6, G7, G8, F1, F2, F3, F4, F5, F6, F7, F8, R1, R2, R3, R4, R5, R6, R7, R8, S4, S1, S2, S3, I1, I2, I3, I4, I5, I6, I7, I8, I9, X1, X2, X3, X4, X5, X6, X7, X8,
}

fun RegisterType.read(vm: Vm) = vm.registers.read(this)

fun RegisterType.write(vm: Vm, value: Long) = vm.registers.write(this, value)

fun String.toRegisterDataType() = when (this.lowercase()) {
	"byte" -> RByte
	"short" -> RShort
	"int" -> RInt
	"long" -> RLong
	"float" -> RFloat
	"double" -> RDouble
	else -> null

}


enum class RegisterDataType {
	RByte, RShort, RInt, RLong, RFloat, RDouble
}


data class RegisterData(val name: RegisterType, var data: Long?, var dataType: RegisterDataType) {

	fun read(): Long? {
		return when (dataType) {
			RByte -> data?.toByte()
			RShort -> data?.toShort()
			RInt -> data?.toInt()
			RLong -> data
			RFloat -> data?.toFloat()
			RDouble -> data?.toDouble()
		}?.toLong()
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
			RDouble -> value.toDouble()
		}.toLong()
	}


	fun settype(newType: RegisterDataType) {
		dataType = when (newType) {
			RByte -> RByte
			RShort -> RShort
			RInt -> RInt
			RLong -> RLong
			RFloat -> RFloat
			RDouble -> RDouble
		}

	}

}