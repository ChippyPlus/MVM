package helpers

import data.registers.enumIdenifiers.*
import functionRegisters
import generalRegisters
import internalFunctionRegisters
import returnRegisters
import systemRegisters

fun fullRegisterReadUnsafe(register: SuperRegisterType): Long? {
	return when (register) {
		SuperRegisterType.G0 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G1 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G2 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G3 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G4 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G5 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G6 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G7 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G8 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G9 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())


		SuperRegisterType.S0 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.S1 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.S2 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.S3 -> systemRegisters.readUnsafe(register.toSystemRegisterType())

		SuperRegisterType.R0 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R1 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R2 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R3 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R4 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R5 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R6 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R7 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R8 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R9 -> returnRegisters.readUnsafe(register.toReturnRegisterType())

		SuperRegisterType.F0 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F1 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F2 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F3 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F4 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F5 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F6 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F7 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F8 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F9 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())

		SuperRegisterType.IF0 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF1 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF2 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF3 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF4 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF5 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF6 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF7 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF8 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF9 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
	}

}