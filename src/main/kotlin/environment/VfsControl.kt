package environment

import json
import kotlinx.serialization.encodeToString
import java.io.File

class VfsControl {
	private val data = json.decodeFromString<List<KFile>>(File("fs.kfs.jsonc").readText()).toMutableList()
	fun listFiles(): MutableSet<String> {
		val out = emptySet<String>().toMutableSet()
		data.forEach { out.add(it.name) }
		return out
	}

	fun exists(name: String): Boolean {
		return data.find { it.name == name } != null
	}

	fun read(name: String): String? {
		return data.find { it.name == name }?.content
	}

	fun rename(old: String, new: String) {
		if (!exists(old)) error("File $old does not exist")
		data.forEachIndexed { index, s ->
			if (s.name == old) {
				data[index] = KFile(new, data[index].content)
			}
		}
		File("fs.kfs.jsonc").writeText(json.encodeToString(data))
	}

	fun write(name: String, content: String) {
		if (!exists(name)) error("File $name does not exist")
		data.forEachIndexed { index, s ->
			if (s.name == name) {
				data[index] = KFile(name, content)
			}
		}
		File("fs.kfs.jsonc").writeText(json.encodeToString(data))
	}
}