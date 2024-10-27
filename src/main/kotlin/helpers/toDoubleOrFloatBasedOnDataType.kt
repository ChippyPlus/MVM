package helpers

import data.registers.RegisterDataType
import data.registers.RegisterType
import registers

fun String.toDoubleOrFloatBasedOnDataType(register: RegisterType): Number {
	return when (registers.registers[register]!!.dataType) {
		RegisterDataType.RFloat -> {
			this.toFloat()
		}

		RegisterDataType.RDouble -> {
			this.toDouble()
		}

		else -> {
			this.toLong()
		}
	}

}