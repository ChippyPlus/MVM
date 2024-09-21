package helpers

import data.registers.RegisterValueType

fun String.toRegisterValueType(): RegisterValueType {
    return when (this) {
        "BYTE" -> RegisterValueType.Byte
        "SHORT" -> RegisterValueType.Short
        "INT" -> RegisterValueType.Int
        "LONG" -> RegisterValueType.Long
        "FLOAT" -> RegisterValueType.Float
        "DOUBLE" -> RegisterValueType.Double
        else -> error("Invalid RegisterValueType $this")
    }
}