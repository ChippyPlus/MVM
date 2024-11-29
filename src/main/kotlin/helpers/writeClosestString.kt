package helpers

fun Helpers.writeClosestString(string: String): Long {
	val spot = vm.heap!!.alloc(string.length + 1)
	for (i in string.indices) {
		val ascii = string[i].code.toLong()
		vm.heap!!.set(i + spot, ascii)
	}
	vm.heap!!.set(spot + string.length, 0)
	println(vm.heap!!.m.toList().subList(0, 30))
	return spot
}