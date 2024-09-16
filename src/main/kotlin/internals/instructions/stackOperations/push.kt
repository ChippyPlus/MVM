package internals.instructions.stackOperations

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead


fun StackOperations.push(registerType: SuperRegisterType) = try {
    @Suppress("UNNECESSARY_NOT_NULL_ASSERTION") this@push.internalStack!!.push(element = fullRegisterRead(register = registerType))
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") with<VMErrors, Unit>(receiver = errors) {
        GeneralStackOperationsException(
            message = "push"
        )
    }
}