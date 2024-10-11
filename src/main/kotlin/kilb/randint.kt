package kilb

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.registerRead
import vm

fun Klib.randMax() = vm.stackOperations.internalStack.push(
	kotlin.random.Random.nextInt(
		0, registerRead(SuperRegisterType.F1).toInt()
	).toLong()
)

