package helpers

import kotlinx.serialization.Serializable

@Serializable
data class ConfigStructure(val stackSize: Int, val memorySize: Int, val hertz: Int)
