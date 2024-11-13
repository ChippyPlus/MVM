package kilb

import data.registers.RegisterType
import helpers.readRegisterString
import internals.Vm

class Klib(val vm: Vm) {
	fun match(fame: String): Boolean {
		when (fame) {

			"strings.strcmp" -> Strings(vm).strcmp()
			"strings.strcat" -> Strings(vm).strcat()
			"strings.strcpy" -> Strings(vm).strcpy()
			"strings.strlen" -> Strings(vm).strlen()
			"println" -> println(vm.helpers.readRegisterString(RegisterType.F1))


			"strings.cheekyfloat" -> cheekyFloat()
			"strings.cheekydouble" -> cheekyDouble()
			"arrays.size" -> Arrays(vm).size()
			"randmax" -> randMax()
			"strtoint" -> strtoint()
			else -> return false
		}
		return true
	}
}