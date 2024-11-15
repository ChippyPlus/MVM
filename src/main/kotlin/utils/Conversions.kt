package utils

import vm.RegisterType

class Conversions


fun String.toRegisterType(): RegisterType? {
	for (r in RegisterType.entries) {
		if (this.uppercase() == r.name) {
			return r
		}
	}
	return null

}