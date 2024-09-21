package data.registers

import data.registers.enumIdenifies.RegisterType

enum class RegisterValueType {
    Byte, Short, Int, Long, Float, Double,
}

data class Register(var value: Double?, var type: RegisterValueType)


class Registers {
    private val registers = mutableMapOf<RegisterType, Register>()

    init {
        for (registerType in RegisterType.entries) {
            registers[registerType] = Register(value = null, type = RegisterValueType.Long)
        }
    }

    fun typeChange(registerType: RegisterType, type: RegisterValueType) {
        registers[registerType]?.type = type
    }

    fun read(registerType: RegisterType): Register {
        return registers[registerType] ?: error("Invalid register: $registerType")
    }

    fun write(registerType: RegisterType, registerDataClass: Register) {
        registers[registerType] = registerDataClass
    }
}