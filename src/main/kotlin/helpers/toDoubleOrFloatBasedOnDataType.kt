package helpers

import data.registers.RegisterDataType
import data.registers.RegisterType
import internals.Vm

fun String.toDoubleOrFloatBasedOnDataType(vm: Vm, register: RegisterType): Long {
	val registers = vm.registers
	val dt = registers.registers[register]!!.dataType
	return when (dt) {
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