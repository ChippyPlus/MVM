package internals.instructions.dataTransfer

import data.registers.RegisterType

@Deprecated(
	"New memory system coming in place", level = DeprecationLevel.WARNING,
	replaceWith = ReplaceWith("Idk Magic?")
)
fun DataTransfer.dealloc(memAddress: RegisterType) = call("dealloc") {
//	internalMemory.write(registers.read(memAddress), 0)
}

