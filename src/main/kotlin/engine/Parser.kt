package engine

import engine.execution.Execute
import java.io.File
import java.util.*


fun Execute.parser(file: File): MutableList<Any> {
	// map of data classes
	val out = emptyArray<Any>().toMutableList()
	val tokens = emptyList<MutableList<String>>().toMutableList()
	for (line in file.readLines()) {
		val _lineParts = emptyList<String>().toMutableList()
		for (token in line.split(' ')) {
			_lineParts.add(token)
		}
		tokens.add(_lineParts)
	}

	val loopStack: Stack<MutableList<List<String>>> = loopData.loopStack
	val indented: MutableList<List<List<String>>> = loopData.indented

	for (line in tokens) {
		if (line.isNotEmpty() && line[0].isNotEmpty() && line[0][0].code == 9 && line[0] != (9).toChar().toString()) {
			val indentedInstruction = line.map {
				if (it[0].code == 9) it.substring(1) else it
			}

			loopStack.peek().add(indentedInstruction)
		} else if (line[0] == "LOOP") {
			loopStack.push(mutableListOf())
		} else if (loopStack.isNotEmpty() && line[0] !in listOf("JMP", "JZ", "JNZ")) {
			if (loopStack.isNotEmpty()) {
				indented.add(loopStack.pop())
			}
		}

	}

	for (line in tokens) {
		out.add(this.matches(line))
	}



	while (loopStack.isNotEmpty()) {
		indented.add(loopStack.pop())
	}

	indented.forEach { println(it) }
	return out
}
