package environment

import kotlinx.serialization.Serializable

@Serializable
data class KFile(val name: String, val content: String)
