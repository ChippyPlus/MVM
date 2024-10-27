package helpers

import data.registers.RegisterDataType
import data.registers.RegisterType
import registers

fun String.toDoubleOrFloatBasedOnDataType(register: RegisterType): Long {
	return when (registers.registers[register]!!.dataType) {
		RegisterDataType.RFloat -> {
			this.toFloat().toBits().toLong()
		}

		RegisterDataType.RDouble -> {
			this.toDouble().toBits()
		}

		else -> {
			this.toLong()
		}
	}

}