package internals.instructions.ioAbstractions

import org.example.errors
import  org.example.kvm
import org.example.kvmInternals.instructions.ioAbstractions.IoAbstractions

fun IoAbstractions.prints() = try {
    println(kvm.stackOperations.internalStack.peek())
} catch (_: Exception) {
    errors.GeneralIoAbstractionsException("prints")
}