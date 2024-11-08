package environment.vfs

import kotlinx.serialization.Serializable


class Formats {

	@Serializable
	data class Meta(
		val size: Int,
		val creationDate: Long,
	)


	@Serializable
	data class Permissions(
		val directory: Boolean = false,
		val write: Boolean = true,
		val read: Boolean = true,
	)

	@Deprecated("Want to now use Ventry for directory's!", replaceWith = ReplaceWith("Ventry"))
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


	@Serializable
	data class Ventry(
		val name: String,
		var content: String? = null,
		val permissions: Permissions = Permissions(directory = false),
		val children: List<Ventry>? = null,
		val meta: Meta = Meta(
			size = checkMetaSizeDirectory(content, permissions, children),
			creationDate = System.currentTimeMillis(),
		),
	)

	companion object {
		fun checkMetaSizeDirectory(content: String?, permissions: Permissions, children: List<Ventry>?): Int {
			return if (permissions.directory) {
				children?.size ?: 0
			} else {
				content?.length ?: 0
			}

		}


	}
}