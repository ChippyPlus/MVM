package helpers

import data.registers.enumIdenifiers.*
import functionRegisters
import generalRegisters
import internalFunctionRegisters
import returnRegisters
import systemRegisters

fun registerWriteUnsafe(register: SuperRegisterType, value: Long?) {
	when (register) {
		SuperRegisterType.G0 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G1 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G2 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G3 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G4 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G5 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G6 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G7 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G8 -> generalRegisters.write(register.toGeneralRegisterType(), value)
		SuperRegisterType.G9 -> generalRegisters.write(register.toGeneralRegisterType(), value)


		SuperRegisterType.S0 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.S1 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.S2 -> systemRegisters.write(register.toSystemRegisterType(), value)
		SuperRegisterType.S3 -> systemRegisters.write(register.toSystemRegisterType(), value)

		SuperRegisterType.R0 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R1 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R2 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R3 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R4 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R5 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R6 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R7 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R8 -> returnRegisters.write(register.toReturnRegisterType(), value)
		SuperRegisterType.R9 -> returnRegisters.write(register.toReturnRegisterType(), value)

		SuperRegisterType.F0 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F1 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F2 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F3 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F4 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F5 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F6 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F7 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F8 -> functionRegisters.write(register.toFunctionRegisterType(), value)
		SuperRegisterType.F9 -> functionRegisters.write(register.toFunctionRegisterType(), value)

		SuperRegisterType.IF0 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF1 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF2 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF3 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF4 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF5 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF6 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF7 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF8 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
		SuperRegisterType.IF9 -> internalFunctionRegisters.write(register.toInternalFunctionType(), value)
	}
}