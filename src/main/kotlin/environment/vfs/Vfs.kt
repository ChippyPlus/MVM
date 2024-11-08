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


			for (part in (name as List<String>)) {
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
		val `I really really dislike the idea of black monkeys if you know what I mean` = mutableSetOf<String>()
		render.forEach { `I really really dislike the idea of black monkeys if you know what I mean`.add(it.name) }
		return `I really really dislike the idea of black monkeys if you know what I mean`
	}

	fun read(name: String): String? {
		val rendered = renderVfs()
		if (name !in deStructureRenderToNames(rendered)) {
			return null
		}
		return rendered.groupBy(Formats.Ventry::name).entries.filter { it.key == name }[0].value[0].content
	}


	fun exits(path: String, r: Set<Formats.Ventry> = renderVfs(), depthX: Int? = null): Boolean {
		val pBits = path.split('/')
		val depth = depthX ?: pBits.size


		val i = pBits[0]
		if (i !in deStructureRenderToNames(r)) {
			return false
		}
		r.forEach {
			if (i == it.name && it.permissions.directory) {
//				println("found new route > $i | ${pBits.size}")
				return exits(pBits.subList(1, pBits.size).joinToString("/"), it.children!!.toSet(), depth)
			}
		}


//		println(r.joinToString())

		return if (path in deStructureRenderToNames(r)) {
			//			println("found new route > $path | ${pBits.size} | final")
			true
		} else {
			//			println("oh no | $path | ${pBits.size}")
			false
		}

	}


	fun flash(ventries: List<Formats.Ventry>) {
		File("src/main/resources/vfs.fs").writeText(
			ProtoBuf.encodeToByteArray(value = ventries).toHexString()
		)
	}

}


fun main() {
	val v = Vfs()
	println(v.exits("home/user/file1.txt"))
//	v.list().forEach(::println)
}

