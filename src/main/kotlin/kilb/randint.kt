package kilb

import data.registers.RegisterType

fun Klib.randMax() = vm.stackOperations.internalStack.push(
	kotlin.random.Random.nextInt(
		0, vm.registers.read(RegisterType.F1).toInt()
	).toLong()
)

