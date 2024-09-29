package engine.execution

data class InstructData(val name: String, val values: Array<Any?>) {
    override fun equals(other: Any?): Boolean { // It's all for the array. Just in case yk
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as InstructData
        if (name != other.name) return false
        if (!values.contentEquals(other.values)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + values.contentHashCode()
        return result
    }
}
