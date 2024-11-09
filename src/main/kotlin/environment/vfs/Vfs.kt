package environment.vfs

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.File


class Vfs {

	init {
		if (!File("src/main/resources/vfs.fs").exists()) {
			File("src/main/resources/vfs.fs").createNewFile()
		}
	}


	fun list(): Set<Formats.Vfile> {
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
	fun new(name: String): Unit? {
		val rendered = renderVfs().toMutableSet()
		if (name in deStructureRenderToNames(rendered)) {
			return null
		}

		rendered.add(
			Formats.Vfile(
				name = name,
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
				rendered.add(Formats.Vfile(i.name, content))
				File("src/main/resources/vfs.fs").writeText(
					ProtoBuf.encodeToByteArray(value = rendered).toHexString()
				)
				return Unit
			}
		}
		return null
	}


	@OptIn(ExperimentalSerializationApi::class)
	fun renderVfs(): Set<Formats.Vfile> =
		ProtoBuf.decodeFromHexString<Set<Formats.Vfile>>(hex = File("src/main/resources/vfs.fs").readText())

	private fun deStructureRenderToNames(render: Set<Formats.Vfile>): Set<String> {
		val `I really really dislike the idea of black monkeys if you know what I mean` = mutableSetOf<String>()
		render.forEach { `I really really dislike the idea of black monkeys if you know what I mean`.add(it.name) }
		return `I really really dislike the idea of black monkeys if you know what I mean`
	}

	fun read(name: String): String? {
		val rendered = renderVfs()
		if (name !in deStructureRenderToNames(rendered)) {
			return null
		}
		return rendered.groupBy(Formats.Vfile::name).entries.filter { it.key == name }[0].value[0].content


	}
}


fun main() {
	Vfs().renderVfs().forEach(::println)
}