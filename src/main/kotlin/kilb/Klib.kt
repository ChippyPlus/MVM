package kilb

class Klib {
	fun match(fame: String): Boolean {
		when (fame) {
			"randmax" -> randMax()
			"strtoint" -> strtoint()
			else -> return false
		}
		return true
	}
}