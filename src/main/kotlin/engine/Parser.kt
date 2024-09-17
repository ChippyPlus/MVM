package engine

import engine.execution.Execute
import java.io.File


fun Execute.parser(file: File): MutableList<Any> {
	val out = emptyArray<Any>().toMutableList()
	val tokens = emptyList<MutableList<String>>().toMutableList()
	for (line in file.readLines()) {
		val _lineParts = emptyList<String>().toMutableList()
		for (token in line.split(' ')) {
			_lineParts.add(token)
		}
		tokens.add(_lineParts)
	}
	for (line in tokens) {
		out.add(this.matches(line))
	}

	return out
}
