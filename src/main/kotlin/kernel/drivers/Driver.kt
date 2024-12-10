package kernel.drivers

interface Driver {
	var name: String
	fun read(where: Long): Long
	fun write(where: Long, what: Long)
}