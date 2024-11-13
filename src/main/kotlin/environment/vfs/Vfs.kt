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
		if (path.isEmpty()) return false
		val parts = path.split("/")
		if (parts.size == 1) return entries.any { it.name == parts[0] }

		var currentEntry: Formats.Ventry?
		for (i in 0 until parts.size - 1) {
			currentEntry = entries.find { it.name == parts[i] && it.permissions.directory }
			entries = currentEntry?.children?.toList() ?: return false
			if (currentEntry.children == null || currentEntry.children!!.find { it.name == parts[i + 1] } == null) {
				return false
			}

		}
		return true
	}

	fun newFiD(path: String, insertable: Formats.Ventry): Boolean {
//		if (!exists(path)) {
//			return false
//		}

		val indexes = intArrayOf().toMutableList()

		var entries = this.list()
		if (path.isEmpty()) {
			return false
		}
		val parts = path.split("/")
		if (parts.size == 1) return entries.any { it.name == parts[0] }
		var currentEntry: Formats.Ventry?
		for (value in 0 until parts.size - 1) {

			currentEntry = entries.find { it.name == parts[value] && it.permissions.directory }
			indexes.add(entries.indexOf(currentEntry))
			entries = currentEntry?.children?.toList() ?: run {
				return false
			}

			if (currentEntry.children == null || currentEntry.children!!.find { it.name == parts[value + 1] } == null) {
				return false
			}
		}
		return true
	}
}


fun main() {
	val f = "home/user/file1.txt"
	println(Vfs().list())
	println(
		Vfs().newFiD(
			f, Formats.Ventry(
				name = "inserted.txt",
				content = "hi",
			)
		)
	)
}

