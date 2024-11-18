package data.vfs

import kotlinx.serialization.Serializable


class Formats {

	@Serializable
	data class Meta(
		val size: Int,
		val creationDate: Long,
	)


	@Serializable
	data class Permissions(
		val write: Boolean = true,
		val read: Boolean = true,
	)

	@Serializable
	data class Vfile(
		val name: String,
		var content: String?,
		val permissions: Permissions = Permissions(),
		val meta: Meta = Meta(
			size = if (content.isNullOrEmpty()) 0 else content.length,
			creationDate = System.currentTimeMillis(),
		),
	)

}