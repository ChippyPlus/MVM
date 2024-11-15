package vm.exceptions

sealed class VmExceptions : Exception() {


	class EmptyStackException : VmExceptions()
	class StackOverflowException : VmExceptions()
	class InvalidRegisterException(override val message: String) : VmExceptions()
	class MemoryAllocationException(override val message: String) : VmExceptions()
	class MemoryAccessException(override val message: String) : VmExceptions()
}