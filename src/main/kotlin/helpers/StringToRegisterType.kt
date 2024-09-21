package helpers

import data.registers.enumIdenifies.RegisterType

fun String.toRegisterType(): RegisterType? {
    return when (this) {
        "G1" -> RegisterType.G1
        "G2" -> RegisterType.G2
        "G3" -> RegisterType.G3
        "G4" -> RegisterType.G4
        "G5" -> RegisterType.G5
        "G6" -> RegisterType.G6
        "G7" -> RegisterType.G7
        "G8" -> RegisterType.G8
        "G9" -> RegisterType.G9
        "G10" -> RegisterType.G10
        "F1" -> RegisterType.F1
        "F2" -> RegisterType.F2
        "F3" -> RegisterType.F3
        "F4" -> RegisterType.F4
        "F5" -> RegisterType.F5
        "F6" -> RegisterType.F6
        "F7" -> RegisterType.F7
        "F8" -> RegisterType.F8
        "F9" -> RegisterType.F9
        "F10" -> RegisterType.F10
        "S1" -> RegisterType.S1
        "S2" -> RegisterType.S2
        "S3" -> RegisterType.S3
        "S4" -> RegisterType.S4
        "S5" -> RegisterType.S5
        "S6" -> RegisterType.S6
        "S7" -> RegisterType.S7
        "S8" -> RegisterType.S8
        "S9" -> RegisterType.S9
        "S10" -> RegisterType.S10
        "R1" -> RegisterType.R1
        "R2" -> RegisterType.R2
        "R3" -> RegisterType.R3
        "R4" -> RegisterType.R4
        "R5" -> RegisterType.R5
        "R6" -> RegisterType.R6
        "R7" -> RegisterType.R7
        "R8" -> RegisterType.R8
        "R9" -> RegisterType.R9
        "R10" -> RegisterType.R10
        "RF1" -> RegisterType.RF1
        "RF2" -> RegisterType.RF2
        "RF3" -> RegisterType.RF3
        "RD4" -> RegisterType.RF4
        "RF5" -> RegisterType.RF5
        "RF6" -> RegisterType.RF6
        "RF7" -> RegisterType.RF7
        "RF8" -> RegisterType.RF8
        "RF9" -> RegisterType.RF9
        "RF10" -> RegisterType.RF10
        else -> null
    }
}