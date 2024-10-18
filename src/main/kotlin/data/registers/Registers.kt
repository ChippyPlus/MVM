package data.registers

class Registers {
	val registers = mutableMapOf<RegisterType, Long?>()

	init {
		for (register in RegisterType.entries) {
			registers[register] = null
		}
	}

	fun readUnsafe(register: RegisterType): Long? = registers[register]

	fun read(register: RegisterType): Long = registers[register]!!

	fun write(register: RegisterType, value: Long) {
		registers[register] = value
	}

	fun writeUnsafe(register: RegisterType, value: Long?) {
		registers[register] = value
	}

}