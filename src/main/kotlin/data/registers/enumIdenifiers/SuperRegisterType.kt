package org.example.data.registers.enumIdenifiers


enum class SuperRegisterType {
    G1,
    G2,
    G3,
    G4,
    S1,
    S2,
    S3,
    S4,
    R1,
    R2,
    R3,
    R4
}

fun SuperRegisterType.toGeneralRegisterType(): GeneralRegisterType {
    return when (this) {
        SuperRegisterType.G1 -> GeneralRegisterType.G1
        SuperRegisterType.G2 -> GeneralRegisterType.G2
        SuperRegisterType.G3 -> GeneralRegisterType.G3
        SuperRegisterType.G4 -> GeneralRegisterType.G4
        SuperRegisterType.S1 -> error("Bad SuperRegisterType \"S1\"")
        SuperRegisterType.S2 -> error("Bad SuperRegisterType \"S2\"")
        SuperRegisterType.S3 -> error("Bad SuperRegisterType \"S3\"")
        SuperRegisterType.S4 -> error("Bad SuperRegisterType \"S4\"")
        SuperRegisterType.R1 -> error("Bad SuperRegisterType \"R1\"")
        SuperRegisterType.R2 -> error("Bad SuperRegisterType \"R2\"")
        SuperRegisterType.R3 -> error("Bad SuperRegisterType \"R3\"")
        SuperRegisterType.R4 -> error("Bad SuperRegisterType \"R4\"")
    }
}

fun SuperRegisterType.toSystemRegisterType(): SystemRegisterType {
    return when (this) {
        SuperRegisterType.G1 -> error("Bad SuperRegisterType \"G1\"")
        SuperRegisterType.G2 -> error("Bad SuperRegisterType \"G2\"")
        SuperRegisterType.G3 -> error("Bad SuperRegisterType \"G3\"")
        SuperRegisterType.G4 -> error("Bad SuperRegisterType \"G4\"")
        SuperRegisterType.S1 -> SystemRegisterType.S1
        SuperRegisterType.S2 -> SystemRegisterType.S2
        SuperRegisterType.S3 -> SystemRegisterType.S3
        SuperRegisterType.S4 -> SystemRegisterType.S4
        SuperRegisterType.R1 -> error("Bad SuperRegisterType \"R1\"")
        SuperRegisterType.R2 -> error("Bad SuperRegisterType \"R2\"")
        SuperRegisterType.R3 -> error("Bad SuperRegisterType \"R3\"")
        SuperRegisterType.R4 -> error("Bad SuperRegisterType \"R4\"")
    }
}

fun SuperRegisterType.toReturnRegisterType(): ReturnRegisterType {
    return when (this) {
        SuperRegisterType.G1 -> error("Bad SuperRegisterType \"G1\"")
        SuperRegisterType.G2 -> error("Bad SuperRegisterType \"G2\"")
        SuperRegisterType.G3 -> error("Bad SuperRegisterType \"G3\"")
        SuperRegisterType.G4 -> error("Bad SuperRegisterType \"G4\"")
        SuperRegisterType.S1 -> error("Bad SuperRegisterType \"S1\"")
        SuperRegisterType.S2 -> error("Bad SuperRegisterType \"S2\"")
        SuperRegisterType.S3 -> error("Bad SuperRegisterType \"S3\"")
        SuperRegisterType.S4 -> error("Bad SuperRegisterType \"S4\"")
        SuperRegisterType.R1 -> ReturnRegisterType.R1
        SuperRegisterType.R2 -> ReturnRegisterType.R2
        SuperRegisterType.R3 -> ReturnRegisterType.R3
        SuperRegisterType.R4 -> ReturnRegisterType.R4
    }
}