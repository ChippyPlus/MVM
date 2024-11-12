package data.registers

import internals.Vm
import kotlin.system.exitProcess


class Registers(val vm: Vm) {
	val errors = vm.errors
	val registers = mutableMapOf<RegisterType, RegisterData>()

	init {
		for (register in RegisterType.entries) {


			registers[register] = RegisterData(


				name = register, data = null, dataType = if (register.name.startsWith('X')) {
					RegisterDataType.RFloat
				} else if (register.name == "R5") {
					RegisterDataType.RFloat
				} else {
					RegisterDataType.RLong
				}
			)
		}
	}

	fun readUnsafe(register: RegisterType): Long? = registers[register]!!.read()




	fun read(register: RegisterType): Long = try {
		val value = registers[register]!!.read()
		value!!

	} catch (_: NullPointerException) {
		errors.NullRegisterException(register)
		exitProcess(1)
	}

	fun write(register: RegisterType, value: Long) {
		registers[register]!!.write(value)
	}

	fun writeUnsafe(register: RegisterType, value: Long?) {
		registers[register]!!.write(value)
	}

}