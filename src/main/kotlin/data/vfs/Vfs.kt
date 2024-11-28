package data.vfs

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.File
import kotlin.system.exitProcess


@OptIn(ExperimentalStdlibApi::class, ExperimentalSerializationApi::class)
class Vfs {
	enum class Mode {
		Remove, Create
	}

	init {
		if (!File("src/main/resources/vfs.fs").exists()) {
			File("src/main/resources/vfs.fs").createNewFile()
			this.flash(listOf(Ventry("/", "", Permissions(directory = true))))

		}
	}

	fun error(message: String): Nothing {
		System.err.println(message)
		exitProcess(1)
	}

	private fun mk(p: Ventry, mode: Mode = Mode.Create) {
		if (p.path.endsWith('/')) {
			p.permissions.directory = true
			p.path = p.path.removeSuffix("/")
		}


		val ren = list()
		val pathPartsUnclear = if (p.path.startsWith('/')) {
			p.path.split('/').subList(1, p.path.split('/').size).toMutableList()
		} else p.path.split('/').toMutableList()

		val pathParts = pathPartsUnclear.filter { it.isNotBlank() }

		println(pathParts)
		var memoized = ""

		if (p !in ren && mode == Mode.Remove) {
			error("Ventry not found")
		}

		for (i in pathParts.withIndex()) {
			memoized += "/${pathParts[i.index]}"
			println(memoized)

			if (i.index == pathParts.size - 1) {
				if (memoized in ren.map(Ventry::path)) {
					if (mode == Mode.Remove) {
						flash(ren - p)
						return
					}
					error("Ventry already exists")
				} else {
					if (mode == Mode.Create) flash(ren + p)
					return
				}
			}

			if (memoized !in ren.map(Ventry::path)) {
				error("Parent directory not found")
			}
		}

	}

	private fun rm(p: Ventry) = mk(p, Mode.Remove)
	fun read(path: String): String {
		val rend = list()
		for (ventry in rend) {
			if (ventry.path == path) {
				return ventry.content
			}
		}
		error("Ventry Not found")
	}

	fun write(path: String, content: String) {
		val rend = list()
		for (ventry in rend.withIndex()) {
			if (ventry.value.path == path) {
				ventry.value.content = content
				println(ventry.value === rend[ventry.index])
			}
		}
		println(rend)
	}

	fun flash(ventries: List<Ventry>) =
		File("src/main/resources/vfs.fs").writeText(ProtoBuf.encodeToByteArray(value = ventries).toHexString())

	fun list(): List<Ventry> =
		ProtoBuf.decodeFromHexString<List<Ventry>>(hex = File("src/main/resources/vfs.fs").readText())


	fun mkFile(path: String) {
		mk(Ventry(path = path, content = "", permissions = Permissions(directory = false, write = true, read = true)))
	}

	fun rmFile(path: String) {
		rm(Ventry(path = path, content = "", permissions = Permissions(directory = false, write = true, read = true)))
	}


}


fun main() {
	val v = Vfs()
	v.rmFile("/sounds")
	v.list().forEach(::println)
}

