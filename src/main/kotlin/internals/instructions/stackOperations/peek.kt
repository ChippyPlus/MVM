package internals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite


fun StackOperations.peek(destination: SuperRegisterType) = try {
    val value = internalStack.peek()
    fullRegisterWrite(destination, value)
} catch (_: Exception) {
    errors.GeneralStackOperationsException("stack")
}