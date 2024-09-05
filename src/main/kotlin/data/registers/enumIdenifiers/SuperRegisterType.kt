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
    R4;


}


fun SuperRegisterType.toGeneralRegisterType(): GeneralRegisterType {
    return when (this) {
        SuperRegisterType.G1 -> GeneralRegisterType.G1
        SuperRegisterType.G2 -> GeneralRegisterType.G2
        SuperRegisterType.G3 -> GeneralRegisterType.G3
        SuperRegisterType.G4 -> GeneralRegisterType.G4
        SuperRegisterType.S1 -> error("Bad SuperRegisterType \"S1\" for generalRegister")
        SuperRegisterType.S2 -> error("Bad SuperRegisterType \"S2\" for generalRegister")
        SuperRegisterType.S3 -> error("Bad SuperRegisterType \"S3\" for generalRegister")
        SuperRegisterType.S4 -> error("Bad SuperRegisterType \"S4\" for generalRegister")
        SuperRegisterType.R1 -> error("Bad SuperRegisterType \"R1\" for generalRegister")
        SuperRegisterType.R2 -> error("Bad SuperRegisterType \"R2\" for generalRegister")
        SuperRegisterType.R3 -> error("Bad SuperRegisterType \"R3\" for generalRegister")
        SuperRegisterType.R4 -> error("Bad SuperRegisterType \"R4\" for generalRegister")
    }
}

fun SuperRegisterType.toSystemRegisterType(): SystemRegisterType {
    return when (this) {
        SuperRegisterType.G1 -> error("Bad SuperRegisterType \"G1\" for systemRegister")
        SuperRegisterType.G2 -> error("Bad SuperRegisterType \"G2\" for systemRegister")
        SuperRegisterType.G3 -> error("Bad SuperRegisterType \"G3\" for systemRegister")
        SuperRegisterType.G4 -> error("Bad SuperRegisterType \"G4\" for systemRegister")
        SuperRegisterType.S1 -> SystemRegisterType.S1
        SuperRegisterType.S2 -> SystemRegisterType.S2
        SuperRegisterType.S3 -> SystemRegisterType.S3
        SuperRegisterType.S4 -> SystemRegisterType.S4
        SuperRegisterType.R1 -> error("Bad SuperRegisterType \"R1\" for systemRegister")
        SuperRegisterType.R2 -> error("Bad SuperRegisterType \"R2\" for systemRegister")
        SuperRegisterType.R3 -> error("Bad SuperRegisterType \"R3\" for systemRegister")
        SuperRegisterType.R4 -> error("Bad SuperRegisterType \"R4\" for systemRegister")
    }
}

fun SuperRegisterType.toReturnRegisterType(): ReturnRegisterType {
    return when (this) {
        SuperRegisterType.G1 -> error("Bad SuperRegisterType \"G1\" for returnRegister")
        SuperRegisterType.G2 -> error("Bad SuperRegisterType \"G2\" for returnRegister")
        SuperRegisterType.G3 -> error("Bad SuperRegisterType \"G3\" for returnRegister")
        SuperRegisterType.G4 -> error("Bad SuperRegisterType \"G4\" for returnRegister")
        SuperRegisterType.S1 -> error("Bad SuperRegisterType \"S1\" for returnRegister")
        SuperRegisterType.S2 -> error("Bad SuperRegisterType \"S2\" for returnRegister")
        SuperRegisterType.S3 -> error("Bad SuperRegisterType \"S3\" for returnRegister")
        SuperRegisterType.S4 -> error("Bad SuperRegisterType \"S4\" for returnRegister")
        SuperRegisterType.R1 -> ReturnRegisterType.R1
        SuperRegisterType.R2 -> ReturnRegisterType.R2
        SuperRegisterType.R3 -> ReturnRegisterType.R3
        SuperRegisterType.R4 -> ReturnRegisterType.R4
    }
}

@Suppress("unused")
fun SuperRegisterType.toRegisterType(): Any {
    return when(this) {
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
    }
}