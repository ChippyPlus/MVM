package data.registers

import data.registers.enumIdenifies.RegisterType
import errors


data class Register(var value: Number?)


class Registers {
    val registers = mutableMapOf<RegisterType, Register>()

    init {
        for (registerType in RegisterType.entries) {
            registers[registerType] = Register(value = 0)
        }
    }

    fun getType(registerType: RegisterType): Any? {
        if (registers[registerType] == null) {
            errors.NullRegisterException(registerType)
        }
        return when (registers[registerType]!!.value) {
            is Byte -> Byte
            is Short -> Short
            is Int -> Int
            is Long -> Long
            is Float -> Float
            is Double -> Double
            else -> null
        }
    }

    fun typeChange(registerType: RegisterType, type: Any?) {
        if (registers[registerType] == null) {
            errors.NullRegisterException(registerType)
        }

        println(type)
        when (type) {
            is Byte.Companion -> registers[registerType]!!.value!!.toByte()
            is Short.Companion -> registers[registerType]!!.value!!.toShort()
            is Int.Companion -> registers[registerType]!!.value!!.toInt()
            is Long.Companion -> registers[registerType]!!.value!!.toLong()
            is Float.Companion -> registers[registerType]!!.value!!.toFloat()
            is Double.Companion -> registers[registerType]!!.value!!.toDouble()
            else -> error("Invalid register type $type")
        }

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