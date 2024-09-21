package data.registers

import data.registers.enumIdenifies.RegisterType
import errors

enum class RegisterValueType {
    Byte, Short, Int, Long, Float, Double,
}

data class Register(var value: Number?, var type: RegisterValueType)


class Registers {
    val registers = mutableMapOf<RegisterType, Register>()

    init {
        for (registerType in RegisterType.entries) {
            registers[registerType] = Register(value = null, type = RegisterValueType.Long)
        }
    }

    fun getType(registerType: RegisterType): RegisterValueType {
        if (registers[registerType] == null) {
            errors.NullRegisterException(registerType)
        }
        return registers[registerType]!!.type
    }

    fun typeChange(registerType: RegisterType, type: RegisterValueType) {
        if (registers[registerType] == null) {
            errors.NullRegisterException(registerType)
        }
        registers[registerType]!!.type = type
        registers[registerType]!!.value
    }

    fun read(registerType: RegisterType): Register {
        if (registers[registerType] == null) {
            errors.InvalidRegisterException(registerType)
        }
        return registers[registerType]!!

    }

    fun write(registerType: RegisterType, registerDataClass: Register) {
        registers[registerType] = registerDataClass
    }
}