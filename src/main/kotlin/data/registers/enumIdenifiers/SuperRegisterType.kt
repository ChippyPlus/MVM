package data.registers.enumIdenifiers

/**
 * Represents a supertype encompassing all register types in the virtual machine.
 *
 * This enumeration allows for a unified way to refer to any register type, regardless of its specific category (General, System, or Return).
 */
enum class SuperRegisterType {
	G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, IF0, IF1, IF2, IF3, IF4, IF5, IF6, IF7, IF8, IF9, R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, S0, S1, S2, S3
}

/**
 * Converts this [SuperRegisterType] to a [GeneralRegisterType], if applicable.
 *
 * @return The corresponding [GeneralRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a general-purpose register.
 */
fun SuperRegisterType.toGeneralRegisterType(): GeneralRegisterType {
	return when (this) {
		SuperRegisterType.G1 -> GeneralRegisterType.G1
		SuperRegisterType.G2 -> GeneralRegisterType.G2
		SuperRegisterType.G3 -> GeneralRegisterType.G3
		SuperRegisterType.G4 -> GeneralRegisterType.G4
		else -> error("Invalid SuperRegisterType \"$this\" for generalRegister")
	}
}


/**
 * Converts this [SuperRegisterType] to a [InternalFunctionRegisterType], if applicable.
 *
 * @return The corresponding [InternalFunctionRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a general-purpose register.
 */
fun SuperRegisterType.toInternalFunctionType(): InternalFunctionRegisterType {
	return when (this) {
		SuperRegisterType.IF1 -> InternalFunctionRegisterType.IF1
		SuperRegisterType.IF2 -> InternalFunctionRegisterType.IF2
		SuperRegisterType.IF3 -> InternalFunctionRegisterType.IF3
		SuperRegisterType.IF4 -> InternalFunctionRegisterType.IF4
		else -> error("Invalid SuperRegisterType \"$this\" for internalFunctionRegister")
	}
}

/**
 * Converts this [SuperRegisterType] to a [SystemRegisterType], if applicable.
 *
 * @return The corresponding [SystemRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a system register.
 */
fun SuperRegisterType.toSystemRegisterType(): SystemRegisterType {
	return when (this) {
		SuperRegisterType.S0 -> SystemRegisterType.S1
		SuperRegisterType.S1 -> SystemRegisterType.S2
		SuperRegisterType.S2 -> SystemRegisterType.S3
		SuperRegisterType.S3 -> SystemRegisterType.S4
		else -> error("Invalid SuperRegisterType \"$this\" for systemRegister")
	}
}

/**
 * Converts this [SuperRegisterType] to a [ReturnRegisterType], if applicable.
 *
 * @return The corresponding [ReturnRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a return register.
 */
fun SuperRegisterType.toReturnRegisterType(): ReturnRegisterType {
	return when (this) {
		SuperRegisterType.R1 -> ReturnRegisterType.R1
		SuperRegisterType.R2 -> ReturnRegisterType.R2
		SuperRegisterType.R3 -> ReturnRegisterType.R3
		SuperRegisterType.R4 -> ReturnRegisterType.R4
		else -> error("Invalid SuperRegisterType \"$this\" for returnRegister")
	}
}

/**
 * Converts this [SuperRegisterType] to a [FunctionRegisterType], if applicable.
 *
 * @return The corresponding [FunctionRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a return register.
 */
fun SuperRegisterType.toFunctionRegisterType(): FunctionRegisterType {
	return when (this) {
		SuperRegisterType.F1 -> FunctionRegisterType.F1
		SuperRegisterType.F2 -> FunctionRegisterType.F2
		SuperRegisterType.F3 -> FunctionRegisterType.F3
		SuperRegisterType.F4 -> FunctionRegisterType.F4
		else -> error("Invalid SuperRegisterType \"$this\" for functionRegister")
	}
}


/**
 * Converts this [SuperRegisterType] to its specific register type ([GeneralRegisterType], [SystemRegisterType], or [ReturnRegisterType]).
 *
 * @return The corresponding register type.
 * @throws IllegalStateException if the conversion is not possible.
 */
@Suppress("unused")
fun SuperRegisterType.toRegisterType(): Any {
	return when (this) {
		SuperRegisterType.G1 -> GeneralRegisterType.G1
		SuperRegisterType.G2 -> GeneralRegisterType.G2
		SuperRegisterType.G3 -> GeneralRegisterType.G3
		SuperRegisterType.G4 -> GeneralRegisterType.G4
		SuperRegisterType.S0 -> SystemRegisterType.S1
		SuperRegisterType.S1 -> SystemRegisterType.S2
		SuperRegisterType.S2 -> SystemRegisterType.S3
		SuperRegisterType.S3 -> SystemRegisterType.S4
		SuperRegisterType.R1 -> ReturnRegisterType.R1
		SuperRegisterType.R2 -> ReturnRegisterType.R2
		SuperRegisterType.R3 -> ReturnRegisterType.R3
		SuperRegisterType.R4 -> ReturnRegisterType.R4
		SuperRegisterType.F1 -> FunctionRegisterType.F1
		SuperRegisterType.F2 -> FunctionRegisterType.F2
		SuperRegisterType.F3 -> FunctionRegisterType.F3
		SuperRegisterType.F4 -> FunctionRegisterType.F4
		SuperRegisterType.IF1 -> InternalFunctionRegisterType.IF1
		SuperRegisterType.IF2 -> InternalFunctionRegisterType.IF2
		SuperRegisterType.IF3 -> InternalFunctionRegisterType.IF3
		SuperRegisterType.IF4 -> InternalFunctionRegisterType.IF4
	}
}