package kilb

import data.registers.RegisterType
import helpers.readRegisterString
import internals.Vm

class Klib(val vm: Vm) {
	val strings = Strings(vm)
	val arrays = Arrays(vm)
	fun match(fame: String): Boolean {
		when (fame) {

			"strings.strcmp" -> strings.strcmp()
			"strings.strcat" -> strings.strcat()
			"strings.strcpy" -> strings.strcpy()
			"strings.strlen" -> strings.strlen()
			"io.println" -> println(vm.helpers.readRegisterString(RegisterType.F1))


			"strings.cheekyfloat" -> cheekyFloat()
			"strings.cheekydouble" -> cheekyDouble()

			"arrays.size" -> arrays.size()
			"arrays.create" -> arrays.create()
			"arrays.set" -> arrays.add()
			"arrays.get" -> arrays.get()

			"randmax" -> randMax()
			"strtoint" -> strtoint()
			else -> return false
		}
		return true
	}


}

fun sreturn(vm: Vm, value: Long) {
	vm.stackOperations.internalStack.push(value)
}