package kilb

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.fullRegisterRead
import vm

fun Klib.randMax() = vm.stackOperations.internalStack.push(
	kotlin.random.Random.nextInt(
		0, fullRegisterRead(SuperRegisterType.F1).toInt()
	).toLong()
)

