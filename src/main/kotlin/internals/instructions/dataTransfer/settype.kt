package internals.instructions.dataTransfer

import data.registers.RegisterDataType
import data.registers.RegisterType
import registers

fun DataTransfer.settype(register: RegisterType, type: RegisterDataType) = call("settype") {
	registers.readData(register).setType(type)
}