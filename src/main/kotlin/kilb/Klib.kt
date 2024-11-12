package kilb

import data.registers.RegisterType
import helpers.readRegisterString


class Klib {
	fun match(fame: String): Boolean {
		when (fame) {

			"strings.strcmp" -> Strings().strcmp()
			"strings.strcat" -> Strings().strcat()
			"strings.strcpy" -> Strings().strcpy()
			"strings.strlen" -> Strings().strlen()
			"println" -> println(readRegisterString(RegisterType.F1))


			"strings.cheekyfloat" -> cheekyFloat()
			"strings.cheekydouble" -> cheekyDouble()
			"arrays.size" -> Arrays().size()
			"randmax" -> randMax()
			"strtoint" -> strtoint()
			else -> return false
		}
		return true
	}
}