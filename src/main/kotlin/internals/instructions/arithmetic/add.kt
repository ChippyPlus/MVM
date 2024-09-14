package internals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters




fun Arithmetic.add(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val A: Long = fullRegisterRead(registerA)
    val B: Long = fullRegisterRead(registerB)
    returnRegisters.write(ReturnRegisterType.R4, A + B)
} catch (e: Exception) {
    errors.GeneralArithmeticException("add")
}