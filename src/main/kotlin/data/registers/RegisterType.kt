package data.registers

/**
 * Represents a supertype encompassing all register types in the virtual machine.
 *
 * This enumeration allows for a unified way to refer to any register type, regardless of its specific category (General, System, or Return).
 */
enum class RegisterType {
	G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, IF0, IF1, IF2, IF3, IF4, IF5, IF6, IF7, IF8, IF9, R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, S0, S1, S2, S3, I0, I1, I2, I3, I4, I5, I6, I7, I8, I9
}

enum class RegisterDataType {
	RByte, RShort, RInt, RLong
}


data class RegisterData(var data: Long?, var dataType: RegisterDataType) {


	fun write(dataIn: Long?) {
		data = when (dataType) {
			RegisterDataType.RByte -> dataIn?.toByte()
			RegisterDataType.RShort -> dataIn?.toShort()
			RegisterDataType.RInt -> dataIn?.toInt()
			RegisterDataType.RLong -> dataIn
		}?.toLong()
	}

	fun setType(type: RegisterDataType) {
		dataType = type
		data = when (type) {
			RegisterDataType.RByte -> data?.toByte()
			RegisterDataType.RShort -> data?.toShort()
			RegisterDataType.RInt -> data?.toInt()
			RegisterDataType.RLong -> data
		}?.toLong()
	}

	fun read(): Long? {
		val out: Number? = when (dataType) {
			RegisterDataType.RByte -> data?.toByte()
			RegisterDataType.RShort -> data?.toShort()
			RegisterDataType.RInt -> data?.toInt()
			RegisterDataType.RLong -> data
		}
		return out?.toLong()
	}
}