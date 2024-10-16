package environment.fileSystem

import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import androidx.sqlite.use

data class F(val id: Long, val name: String, val content: String?, val parent: String, val meta: String)


val databaseConnection: SQLiteConnection = BundledSQLiteDriver().open("src/main/resources/fs.db")

fun main() {
	Fs().make("meowSection", "info", 'd')
	databaseConnection.close()
}


class Fs

fun Fs.make(name: String, parent: String, type: Char) {
	val p = parse()
	val hashCode1 = arrayOf(name, parent).contentHashCode()

	for (i in p) {
		val (_, internal_name, _, internal_parent) = i
		val hashCode = arrayOf(internal_name, internal_parent).contentHashCode()

		if (hashCode == hashCode1) {
			println("ERROR, File already exists")
			return
		}
	}
	databaseConnection.execSQL("insert into vfs (id,name,content,parent,data) VALUES (${hashCode1}, '$name',NULL,'$parent','$type')")
}

fun Fs.parse(): MutableSet<F> {
	val into = mutableSetOf<F>()
	databaseConnection.prepare("SELECT * FROM vfs").use {
		val names = it.getColumnNames()
		while (it.step()) {
			val tmpL = mutableListOf<Any?>()
			for (i in names.indices) {
				tmpL.add(it.getText(i).ifEmpty { null })
			}
			into.add(
				F(
					id = (tmpL[0] as String).toLong(),
					name = tmpL[1] as String,
					content = tmpL[2] as String?,
					parent = tmpL[3] as String,
					meta = tmpL[4] as String
				)
			)
		}
	}
	return into
}