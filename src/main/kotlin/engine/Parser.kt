package engine


fun Parser(f: List<String>): MutableList<List<String>> {
    val finalOut = emptyArray<List<String>>().toMutableList()

    for (line in f) {
        val l = line.split(' ').map {
            if (it.toDoubleOrNull() != null) {
                it.toDouble()
            } else {
                it
            }
        }
        @Suppress("UNCHECKED_CAST")
        finalOut.add(l as List<String>)
    }
    return finalOut
}
