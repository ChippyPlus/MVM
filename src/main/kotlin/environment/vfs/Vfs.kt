package environment.vfs

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.File


@OptIn(ExperimentalStdlibApi::class, ExperimentalSerializationApi::class)
class Vfs {

	init {
		if (!File("src/main/resources/vfs.fs").exists()) {
			File("src/main/resources/vfs.fs").createNewFile()
		}
	}


	fun list(): Set<Formats.Ventry> {
		return renderVfs()
	}

	@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
	fun delete(name: String): Unit? {
		val rendered = renderVfs().toMutableSet()
		for (i in rendered) {
			if (i.name == name) {
				rendered.remove(i)
				File("src/main/resources/vfs.fs").writeText(ProtoBuf.encodeToByteArray(value = rendered).toHexString())
				return Unit
			}

		}
		return null
	}


	@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
	fun newDir(name: String): Unit? {
		val rendered = renderVfs().toMutableSet()
		if (name in deStructureRenderToNames(rendered)) {
			return null
		}
		rendered.add(
			Formats.Ventry(
				name = name, content = null, permissions = Formats.Permissions(directory = true), children = emptyList()
			)
		)
		File("src/main/resources/vfs.fs").writeText(
			ProtoBuf.encodeToByteArray(value = rendered).toHexString()
		)
		return Unit
	}


	@Suppress("UNREACHABLE_CODE")
	@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
	fun new(nameX: String): Unit? {
		val rendered = renderVfs().toMutableSet()

		val name: Any = if ('/' in nameX) nameX.split('/') else nameX

		if (name is List<*>) {


			for (part in name) {
				if (part in deStructureRenderToNames(rendered)) {
					rendered.forEach { if (part == it.name && it.permissions.directory) println("found route > $part") }
				}
			}
		}
		return Unit

		/// warning, everything below this line is unreachable!

		if (name in deStructureRenderToNames(rendered)) {
			return null
		}

		rendered.add(
			Formats.Ventry(
				name = name as String,
				content = null,
			)
		)
		File("src/main/resources/vfs.fs").writeText(
			ProtoBuf.encodeToByteArray(value = rendered).toHexString()
		)
		return Unit
	}


	@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
	fun write(name: String, content: String): Unit? {
		val rendered = renderVfs().toMutableSet()
		if (name !in deStructureRenderToNames(rendered)) {
			return null
		}
		for (i in rendered) {
			if (i.name == name) {
				rendered.remove(i)
				rendered.add(Formats.Ventry(i.name, content))
				File("src/main/resources/vfs.fs").writeText(
					ProtoBuf.encodeToByteArray(value = rendered).toHexString()
				)
				return Unit
			}
		}
		return null
	}


	fun renderVfs(): Set<Formats.Ventry> =
		ProtoBuf.decodeFromHexString<Set<Formats.Ventry>>(hex = File("src/main/resources/vfs.fs").readText())

	private fun deStructureRenderToNames(render: Set<Formats.Ventry>): Set<String> {
		val strings = mutableSetOf<String>()
		render.forEach { strings.add(it.name) }
		return strings
	}

	fun read(name: String): String? {
		val rendered = renderVfs()
		if (name !in deStructureRenderToNames(rendered)) {
			return null
		}
		return rendered.groupBy(Formats.Ventry::name).entries.filter { it.key == name }[0].value[0].content
	}


	fun flash(ventries: Set<Formats.Ventry>) {
		File("src/main/resources/vfs.fs").writeText(
			ProtoBuf.encodeToByteArray(value = ventries).toHexString()
		)
	}


	fun newFiD(path: String, insertable: Formats.Ventry): Boolean {
		val indexes = mutableListOf<Int>()
		var entries = this.list().toMutableList() // Work on a mutable copy

		if (path.isEmpty()) {
			return false
		}

		val parts = path.split("/")
		if (parts.size == 1) {
			// Handle the case where the path is just a single file name
			entries.add(insertable)
			this.flash(entries.toSet()) // Update the VFS
			return true
		}

		var currentEntry: Formats.Ventry?
		for (i in 0 until parts.size - 1) {
			currentEntry = entries.find { it.name == parts[i] && it.permissions.directory }
			if (currentEntry == null) {
				// Path component not found, create it if it's the last one
				if (i == parts.size - 2) {
					currentEntry = Formats.Ventry(
						name = parts[i],
						permissions = Formats.Permissions(directory = true),
						children = mutableListOf(insertable)
					)
					entries.add(currentEntry)
					this.flash(entries.toSet())
					return true
				} else {
					return false
				}
			}
			indexes.add(entries.indexOf(currentEntry))
			entries = currentEntry.children?.toMutableList() ?: return false
		}

		// Convert to LinkedHashSet to preserve order and ensure uniqueness
		val childrenSet = entries.toCollection(LinkedHashSet())
		childrenSet.add(insertable)

		// Traverse back up the indexes, updating the children lists
		for (i in indexes.size - 1 downTo 0) {
			val parentIndex = indexes[i]
			val parent = this.list().toList()[parentIndex] // Get the parent from the original list
			val updatedParent = parent.copy(children = childrenSet.toList())
			this.flash(this.list().toMutableList().also { it[parentIndex] = updatedParent }.toSet()) // Update the VFS
			entries = this.list().toMutableList() // Refresh entries for the next iteration
		}

		return true
	}

	fun exists(path: String, entriesX: Set<Formats.Ventry> = this.list()): Boolean {
		var entries = entriesX
		if (path.isEmpty()) return false
		val parts = path.split("/")
		if (parts.size == 1) return entries.any { it.name == parts[0] }
		var currentEntry: Formats.Ventry?
		for (i in 0 until parts.size - 1) {
			currentEntry = entries.find { it.name == parts[i] && it.permissions.directory }
			entries = currentEntry?.children?.toSet() ?: return false
			if (currentEntry.children == null || currentEntry.children!!.find { it.name == parts[i + 1] } == null) {
				return false
			}
		}
		return true
	}

}


fun main() {
	val f = "home/user/"
	val v = Vfs()
	Vfs().list().forEach(::println)
//	v.list().toList()[0].children!!.forEach(::println)

//	v.newFiD(
//		f, Formats.Ventry(
//			name = "inserted.txt",
//			content = "hi",
//		)
//	)
//	v.list()[0].children!![0].children!!.forEach(::println)

	println(Vfs().exists("home/user/inserted.txt"))
//	v.flash(myFileSystem.toSet())
}

