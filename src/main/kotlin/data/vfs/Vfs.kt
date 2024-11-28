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
			this.flash(listOf(Ventry("/", null, Permissions(directory = true))))

		}
	}

	fun error(message: String) {
		System.err.println(message)
		exitProcess(1)
	}

	fun mk(p: Ventry, mode: Mode = Mode.Create) {
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
		for (i in pathParts.withIndex()) {
			memoized += "/${pathParts[i.index]}"
			println(memoized)


			if (i.index == pathParts.size - 1) {
				if (memoized in ren.map(Ventry::path)) {
					error("Ventry already exists")
				} else {
					if (mode == Mode.Create) flash(ren + p)
					else if (mode == Mode.Remove) flash(ren - p)
					return
				}

			}

			if (memoized !in ren.map(Ventry::path)) {
				error("Parent directory not found")
			}
		}

	}

	fun rm(p: Ventry) = mk(p, Mode.Remove)

	fun flash(ventries: List<Ventry>) =
		File("src/main/resources/vfs.fs").writeText(ProtoBuf.encodeToByteArray(value = ventries).toHexString())

	fun list(): List<Ventry> =
		ProtoBuf.decodeFromHexString<List<Ventry>>(hex = File("src/main/resources/vfs.fs").readText())
}


fun main() {
	val v = Vfs()
	v.rm(Ventry(path = "/sounds/cats/meow.txt", content = "Meow!", Permissions(directory = false, write = false)))
	v.list().forEach(::println)
}

