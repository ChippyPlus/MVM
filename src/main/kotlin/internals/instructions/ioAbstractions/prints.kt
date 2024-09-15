package internals.instructions.ioAbstractions

import org.example.errors
import org.example.kvm

fun IoAbstractions.prints(): Unit = try {
    println(message = with(kvm) {
        return@with this.stackOperations.run { return@run this@run.internalStack.peek() }
    })
} catch (_: Exception) {
    errors.run {
        this@run.GeneralIoAbstractionsException(message = "prints")
    }
}