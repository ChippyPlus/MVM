package kilb

class Klib {
	fun match(fame: String): Boolean {
		when (fame) {
			"arrays.size" -> Arrays().size()
			"randmax" -> randMax()
			"strtoint" -> strtoint()
			else -> return false
		}
		return true
	}
}