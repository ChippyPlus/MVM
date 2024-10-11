package helpers

import data.registers.enumIdenifiers.*
import functionRegisters
import generalRegisters
import internalFunctionRegisters
import returnRegisters
import systemRegisters

fun fullRegisterReadUnsafe(register: SuperRegisterType): Long? {
	return when (register) {
		SuperRegisterType.G1 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G2 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G3 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.G4 -> generalRegisters.readUnsafe(register.toGeneralRegisterType())
		SuperRegisterType.S0 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.S1 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.S2 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.S3 -> systemRegisters.readUnsafe(register.toSystemRegisterType())
		SuperRegisterType.R1 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R2 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R3 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.R4 -> returnRegisters.readUnsafe(register.toReturnRegisterType())
		SuperRegisterType.F1 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F2 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F3 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.F4 -> functionRegisters.readUnsafe(register.toFunctionRegisterType())
		SuperRegisterType.IF1 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF2 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF3 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
		SuperRegisterType.IF4 -> internalFunctionRegisters.readUnsafe(register.toInternalFunctionType())
	}

}