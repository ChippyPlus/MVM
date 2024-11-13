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


	fun list(): List<Formats.Ventry> {
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
		val rendered = renderVfs().toMutableList()
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
		val rendered = renderVfs().toMutableList()

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
		val rendered = renderVfs().toMutableList()
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


	fun renderVfs(): List<Formats.Ventry> =
		ProtoBuf.decodeFromHexString<List<Formats.Ventry>>(hex = File("src/main/resources/vfs.fs").readText())

	private fun deStructureRenderToNames(render: List<Formats.Ventry>): Set<String> {
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


	fun flash(ventries: List<Formats.Ventry>) {
		File("src/main/resources/vfs.fs").writeText(
			ProtoBuf.encodeToByteArray(value = ventries).toHexString()
		)
	}

	fun exists(path: String, entriesX: List<Formats.Ventry> = this.list()): Boolean {
		var entries = entriesX
		if (path.isEmpty()) {
			println(1)
			return false
		}
		val parts = path.split("/")
		if (parts.size == 1) {
			println(2)
			return entries.any { it.name == parts[0] }
		}

		var currentEntry: Formats.Ventry?
		for (i in 0 until parts.size - 1) {
			currentEntry = entries.find { it.name == parts[i] && it.permissions.directory }
			entries = currentEntry?.children?.toList() ?: return false
			if (currentEntry.children == null || currentEntry.children!!.find { it.name == parts[i + 1] && it.permissions.directory } == null) {
				println(3)
				return false
			}

		}
		return true
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
			this.flash(entries) // Update the VFS
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
					this.flash(entries)
					return true
				} else {
					return false
				}
			}
			indexes.add(entries.indexOf(currentEntry))
			entries = currentEntry.children?.toMutableList() ?: return false
		}

		// Insert at the correct position
		entries.add(insertable)
		// Traverse back up the indexes, updating the children lists
		for (i in indexes.size - 1 downTo 0) {
			val parentIndex = indexes[i]
			val parent = this.list()[parentIndex] // Get the parent from the original list
			val updatedParent = parent.copy(children = entries)
			this.flash(this.list().toMutableList().also { it[parentIndex] = updatedParent }) // Update the VFS
			entries = this.list().toMutableList() // Refresh entries for the next iteration
		}

		return true
	}
}


fun main() {
	val f = "home/user"
	val v = Vfs()
//	Vfs().list().forEach(::println)

//	Vfs().newFiD(
//		f, Formats.Ventry(
//			name = "inserted.txt",
//			content = "hi",
//		)
//	)
	v.list()[0].children!![0].children!!.forEach(::println)

	println(Vfs().exists("home/user/file1.txt"))
}

