package engine.parseEngine

fun Parse.tokenV1Generation(): List<List<String>> {
	val tokens = mutableListOf<List<String>>()
	for (line in file) {
		val secretLineParts = emptyList<String>().toMutableList()
		for (token in line.split(' ')) {
			if (token.isEmpty()) continue
			secretLineParts.add(token)
		}
		tokens.add(secretLineParts.toList())
	}
	return tokens
}