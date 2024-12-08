package kilb

import data.registers.RegisterType

fun Klib.randMax() = kp.vm.stackOperations.internalStack.push(
	kotlin.random.Random.nextInt(
		0, kp.vm.registers.read(RegisterType.F1).toInt()
	).toLong()
)

