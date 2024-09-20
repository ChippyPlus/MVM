package data.registers.enumIdenifiers

/**
 * Represents a supertype encompassing all register types in the virtual machine.
 *
 * This enumeration allows for a unified way to refer to any register type, regardless of its specific category (General, System, or Return).
 */
enum class SuperRegisterType {
    G1, G2, G3, G4, S1, S2, S3, S4, R1, R2, R3, R4, F1, F2, F3, F4;
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
 * Converts this [SuperRegisterType] to a [SystemRegisterType], if applicable.
 *
 * @return The corresponding [SystemRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a system register.
 */
fun SuperRegisterType.toSystemRegisterType(): SystemRegisterType {
    return when (this) {
        SuperRegisterType.S1 -> SystemRegisterType.S1
        SuperRegisterType.S2 -> SystemRegisterType.S2
        SuperRegisterType.S3 -> SystemRegisterType.S3
        SuperRegisterType.S4 -> SystemRegisterType.S4
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
 * Converts this [SuperRegisterType] to a [FloatingRegisterType], if applicable.
 *
 * @return The corresponding [ReturnRegisterType].
 * @throws IllegalStateException if this [SuperRegisterType] is not a return register.
 */
fun SuperRegisterType.toFloatingRegisterType(): FloatingRegisterType {
    return when (this) {
        SuperRegisterType.R1 -> FloatingRegisterType.R1
        SuperRegisterType.F1 -> FloatingRegisterType.F1
        SuperRegisterType.F2 -> FloatingRegisterType.F2
        SuperRegisterType.F3 -> FloatingRegisterType.F3
        SuperRegisterType.F4 -> FloatingRegisterType.F4
        else -> error("Invalid SuperRegisterType \"$this\" for returnRegister")
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
        SuperRegisterType.S1 -> SystemRegisterType.S1
        SuperRegisterType.S2 -> SystemRegisterType.S2
        SuperRegisterType.S3 -> SystemRegisterType.S3
        SuperRegisterType.S4 -> SystemRegisterType.S4
        SuperRegisterType.R1 -> ReturnRegisterType.R1
        SuperRegisterType.R2 -> ReturnRegisterType.R2
        SuperRegisterType.R3 -> ReturnRegisterType.R3
        SuperRegisterType.R4 -> ReturnRegisterType.R4
        SuperRegisterType.F1 -> FloatingRegisterType.F1
        SuperRegisterType.F2 -> FloatingRegisterType.F2
        SuperRegisterType.F3 -> FloatingRegisterType.F3
        SuperRegisterType.F4 -> FloatingRegisterType.F4
    }
}