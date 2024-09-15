package internals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterRead


fun StackOperations.push(registerType: SuperRegisterType) = try {
    @Suppress("UNNECESSARY_NOT_NULL_ASSERTION") this@push.internalStack!!.push(element = fullRegisterRead(register = registerType))
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") with<VMErrors, Unit>(receiver = errors) {
        GeneralStackOperationsException(
            message = "push"
        )
    }
}