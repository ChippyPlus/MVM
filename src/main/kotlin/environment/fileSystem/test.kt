package environment.fileSystem

import androidx.sqlite.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver


class Fs() {
	val databaseConnection = BundledSQLiteDriver().open("src/main/resources/fs.db")

	init {
		databaseConnection.execSQL( // make the main table
			"CREATE TABLE IF NOT EXISTS fs (name TEXT PRIMARY KEY, content TEXT, directory, data TEXT)"
		)
	}


}


fun main() {
	val fs = Fs()
	fs.newFile("init")
	fs.newFile("run_me.kar")
	fs.readAllNames()


	fs.databaseConnection.close()
}


fun Fs.readAllNames() {
	databaseConnection.prepare("SELECT * FROM fs").use { row ->
		while (row.step()) {
			println("Action item: ${row.getText(0)}")
		}
	}
}

fun Fs.newFile(name: String) {
	databaseConnection.prepare(
		"INSERT OR IGNORE INTO fs (name, content, directory, data) VALUES (? ,?, ?, ?)"
	).use { stmt ->
		stmt.bindText(index = 1, value = name)
		stmt.bindText(index = 2, value = "")
		stmt.bindText(index = 3, value = ".")
		stmt.bindText(index = 4, value = "f")
		stmt.step()
	}
}