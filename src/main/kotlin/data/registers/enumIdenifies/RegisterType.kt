package data.registers.enumIdenifies

import data.registers.RegisterValueType
import registers


enum class RegisterType {
    G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, RF1, RF2, RF3, RF4, RF5, RF6, RF7, RF8, RF9, RF10;

    fun getType(): RegisterValueType {
        return registers.getType(this)
    }
}