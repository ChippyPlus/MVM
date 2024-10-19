package environment.errorsCatchable

enum class ErrorType(val code: Byte) {
	FILE_NOT_FOUND(0),
	FILE_ALREADY_EXISTS(1),
	LONG_OVERFLOW(2),
	LONG_UNDERFLOW(3),
	DIVIDE_BY_0(4),
	STACK_OVERFLOW(5),
	STACK_UNDERFLOW(6),
}