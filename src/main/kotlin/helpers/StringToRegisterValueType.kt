package helpers


fun String.toRegisterValueType(): Any {
    return when (this) {
        "BYTE" -> Byte
        "SHORT" -> Short
        "INT" -> Int
        "LONG" -> Long
        "FLOAT" -> Float
        "DOUBLE" -> Double
        else -> error("Invalid RegisterValueType $this")
    }
}