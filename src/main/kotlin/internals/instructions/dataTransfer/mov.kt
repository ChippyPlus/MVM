package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


/**
 * Moves the value from a source register to a destination register.
 * Supports all register types: General Purpose (G1-G4), System (S1-S4), and Return (R1-R4).
 * Throws an IllegalStateException if the move operation fails.
 */

fun DataTransfer.mov(Source: SuperRegisterType, Destination: SuperRegisterType) {
    try {
        val value = fullRegisterRead(Source)
        fullRegisterWrite(Destination, value)
    } catch (e: Exception) {
        throw IllegalStateException("Moving failed", e)
    }
}