package engine

import data.registers.enumIdenifies.RegisterType
import helpers.toRegisterValueType
import vm.instructions.*

fun runner(l: List<Any>) {
	when (l[0]) {
		"LIT" -> Lit(l[1] as RegisterType, l[2] as Double).execute()
		"MOV" -> Mov(l[1] as RegisterType, l[2] as RegisterType).execute()
		"PRINTR" -> Printr(l[1] as RegisterType).execute()
		"SETTYPE" -> SetType(l[1] as RegisterType, (l[2] as String).toRegisterValueType()).execute()
		else -> println("unknown type: ${l[0]}")
	}
}
