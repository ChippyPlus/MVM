package kilb

class Klib {
	fun match(fame: String): Boolean {
		when (fame) {
			"randmax" -> randMax()
			else -> return false
		}
		return true
	}
}