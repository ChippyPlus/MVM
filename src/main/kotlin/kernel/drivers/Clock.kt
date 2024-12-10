package kernel.drivers

class Clock() : Driver {
	override var name: String = "Clock"

	override fun read(where: Long): Long {
		return System.currentTimeMillis()
	}

	override fun write(where: Long, what: Long) {
	}

}