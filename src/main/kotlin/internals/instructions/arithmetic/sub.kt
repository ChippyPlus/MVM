package internals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters


fun Arithmetic.sub(registerA: SuperRegisterType, registerB: SuperRegisterType) = try {
    val A = fullRegisterRead(registerA)
    val B = fullRegisterRead(registerB)
    returnRegisters.write(ReturnRegisterType.R4, A - B)
} catch (e: Exception) {
    errors.GeneralArithmeticException("sub")
}