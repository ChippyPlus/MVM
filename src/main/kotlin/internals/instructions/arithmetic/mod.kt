package internals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.ReturnRegisterType.R4
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters

fun Arithmetic.mod(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val A: Long = fullRegisterRead(register = registerA)
    val B: Long = fullRegisterRead(register = registerB)
    returnRegisters.run {
        write(
            registers = R4,
            value = A.run {
                mod(other = B)
            },
        )
    }
} catch (e: Exception) {
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "mod") }
}