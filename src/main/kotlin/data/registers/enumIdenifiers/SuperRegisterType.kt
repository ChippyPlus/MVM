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
		SuperRegisterType.G0 -> GeneralRegisterType.G0
		SuperRegisterType.G1 -> GeneralRegisterType.G1
		SuperRegisterType.G2 -> GeneralRegisterType.G2
		SuperRegisterType.G3 -> GeneralRegisterType.G3
		SuperRegisterType.G4 -> GeneralRegisterType.G4
		SuperRegisterType.G5 -> GeneralRegisterType.G5
		SuperRegisterType.G6 -> GeneralRegisterType.G6
		SuperRegisterType.G7 -> GeneralRegisterType.G7
		SuperRegisterType.G8 -> GeneralRegisterType.G8
		SuperRegisterType.G9 -> GeneralRegisterType.G9
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
		SuperRegisterType.IF0 -> InternalFunctionRegisterType.IF0
		SuperRegisterType.IF1 -> InternalFunctionRegisterType.IF1
		SuperRegisterType.IF2 -> InternalFunctionRegisterType.IF2
		SuperRegisterType.IF3 -> InternalFunctionRegisterType.IF3
		SuperRegisterType.IF4 -> InternalFunctionRegisterType.IF4
		SuperRegisterType.IF5 -> InternalFunctionRegisterType.IF5
		SuperRegisterType.IF6 -> InternalFunctionRegisterType.IF6
		SuperRegisterType.IF7 -> InternalFunctionRegisterType.IF7
		SuperRegisterType.IF8 -> InternalFunctionRegisterType.IF8
		SuperRegisterType.IF9 -> InternalFunctionRegisterType.IF9
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
		SuperRegisterType.S0 -> SystemRegisterType.S0
		SuperRegisterType.S1 -> SystemRegisterType.S1
		SuperRegisterType.S2 -> SystemRegisterType.S2
		SuperRegisterType.S3 -> SystemRegisterType.S3
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
		SuperRegisterType.R0 -> ReturnRegisterType.R0
		SuperRegisterType.R1 -> ReturnRegisterType.R1
		SuperRegisterType.R2 -> ReturnRegisterType.R2
		SuperRegisterType.R3 -> ReturnRegisterType.R3
		SuperRegisterType.R4 -> ReturnRegisterType.R4
		SuperRegisterType.R5 -> ReturnRegisterType.R5
		SuperRegisterType.R6 -> ReturnRegisterType.R6
		SuperRegisterType.R7 -> ReturnRegisterType.R7
		SuperRegisterType.R8 -> ReturnRegisterType.R8
		SuperRegisterType.R9 -> ReturnRegisterType.R9
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
		SuperRegisterType.F0 -> FunctionRegisterType.F1
		SuperRegisterType.F1 -> FunctionRegisterType.F1
		SuperRegisterType.F2 -> FunctionRegisterType.F2
		SuperRegisterType.F3 -> FunctionRegisterType.F3
		SuperRegisterType.F4 -> FunctionRegisterType.F4
		SuperRegisterType.F5 -> FunctionRegisterType.F5
		SuperRegisterType.F6 -> FunctionRegisterType.F6
		SuperRegisterType.F7 -> FunctionRegisterType.F7
		SuperRegisterType.F8 -> FunctionRegisterType.F8
		SuperRegisterType.F9 -> FunctionRegisterType.F9
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
		SuperRegisterType.G0 -> GeneralRegisterType.G0
		SuperRegisterType.G1 -> GeneralRegisterType.G1
		SuperRegisterType.G2 -> GeneralRegisterType.G2
		SuperRegisterType.G3 -> GeneralRegisterType.G3
		SuperRegisterType.G4 -> GeneralRegisterType.G4
		SuperRegisterType.G5 -> GeneralRegisterType.G5
		SuperRegisterType.G6 -> GeneralRegisterType.G6
		SuperRegisterType.G7 -> GeneralRegisterType.G7
		SuperRegisterType.G8 -> GeneralRegisterType.G8
		SuperRegisterType.G9 -> GeneralRegisterType.G9


		SuperRegisterType.S0 -> SystemRegisterType.S0
		SuperRegisterType.S1 -> SystemRegisterType.S1
		SuperRegisterType.S2 -> SystemRegisterType.S2
		SuperRegisterType.S3 -> SystemRegisterType.S3

		SuperRegisterType.R0 -> SuperRegisterType.R0
		SuperRegisterType.R1 -> ReturnRegisterType.R1
		SuperRegisterType.R2 -> ReturnRegisterType.R2
		SuperRegisterType.R3 -> ReturnRegisterType.R3
		SuperRegisterType.R4 -> ReturnRegisterType.R4
		SuperRegisterType.R5 -> ReturnRegisterType.R5
		SuperRegisterType.R6 -> ReturnRegisterType.R6
		SuperRegisterType.R7 -> ReturnRegisterType.R7
		SuperRegisterType.R8 -> ReturnRegisterType.R8
		SuperRegisterType.R9 -> ReturnRegisterType.R9

		SuperRegisterType.F0 -> FunctionRegisterType.F0
		SuperRegisterType.F1 -> FunctionRegisterType.F1
		SuperRegisterType.F2 -> FunctionRegisterType.F2
		SuperRegisterType.F3 -> FunctionRegisterType.F3
		SuperRegisterType.F4 -> FunctionRegisterType.F4
		SuperRegisterType.F5 -> FunctionRegisterType.F5
		SuperRegisterType.F6 -> FunctionRegisterType.F6
		SuperRegisterType.F7 -> FunctionRegisterType.F7
		SuperRegisterType.F8 -> FunctionRegisterType.F8
		SuperRegisterType.F9 -> FunctionRegisterType.F9

		SuperRegisterType.IF0 -> InternalFunctionRegisterType.IF0
		SuperRegisterType.IF1 -> InternalFunctionRegisterType.IF1
		SuperRegisterType.IF2 -> InternalFunctionRegisterType.IF2
		SuperRegisterType.IF3 -> InternalFunctionRegisterType.IF3
		SuperRegisterType.IF4 -> InternalFunctionRegisterType.IF4
		SuperRegisterType.IF5 -> InternalFunctionRegisterType.IF5
		SuperRegisterType.IF6 -> InternalFunctionRegisterType.IF6
		SuperRegisterType.IF7 -> InternalFunctionRegisterType.IF7
		SuperRegisterType.IF8 -> InternalFunctionRegisterType.IF8
		SuperRegisterType.IF9 -> InternalFunctionRegisterType.IF9
	}
}