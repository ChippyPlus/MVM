package data.vfs

import kotlinx.serialization.Serializable

@Serializable
data class Permissions(var directory: Boolean = false, var write: Boolean = true, var read: Boolean = true)


@Serializable
data class Ventry(var path: String, var content: String? = null, var permissions: Permissions = Permissions())