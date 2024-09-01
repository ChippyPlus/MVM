package org.example.helpers

import org.example.data.registers.enumIdenifiers.SuperRegisterType

fun String.toSuperRegisterType(): SuperRegisterType {
    return when (this) {
        "G1" -> SuperRegisterType.G1
        "G2" -> SuperRegisterType.G2
        "G3" -> SuperRegisterType.G3
        "G4" -> SuperRegisterType.G4
        "S1" -> SuperRegisterType.S1
        "S2" -> SuperRegisterType.S2
        "S3" -> SuperRegisterType.S3
        "S4" -> SuperRegisterType.S4
        "R1" -> SuperRegisterType.R1
        "R2" -> SuperRegisterType.R2
        "R3" -> SuperRegisterType.R3
        "R4" -> SuperRegisterType.R4
        else -> error("Bad superRegisterType '$this'.")
    }
}