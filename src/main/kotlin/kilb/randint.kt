package kilb

import data.registers.RegisterType
import registers
import vm

fun Klib.randMax() = vm.stackOperations.internalStack.push(
	kotlin.random.Random.nextInt(
		0, registers.read(RegisterType.F1).toInt()
	).toLong()
)

