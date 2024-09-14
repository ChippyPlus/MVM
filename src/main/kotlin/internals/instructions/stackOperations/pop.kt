package internals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite


fun StackOperations.pop(destination: SuperRegisterType) = try {
    val value = internalStack.pop()
    fullRegisterWrite(destination, value)
} catch (_: Exception) {
    errors.GeneralStackOperationsException("pop")
}