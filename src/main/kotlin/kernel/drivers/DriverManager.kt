package kernel.drivers

import data.registers.RegisterType
import data.registers.read
import data.registers.write
import kernel.process.KProcess

class DriverManager {
	val loaded = mutableMapOf<KProcess, MutableList<Driver>>()
	val driversList = listOf<Driver>(
		Clock()
	)


	fun read(kp: KProcess, driverId: RegisterType, where: RegisterType) {
		RegisterType.R2.write(kp.vm, driversList[driverId.read(kp.vm).toInt()].read(where.read(kp.vm)))

	}

	fun write(kp: KProcess, driverId: RegisterType, where: RegisterType, what: RegisterType) {
		driversList[driverId.read(kp.vm).toInt()].write(where.read(kp.vm), what.read(kp.vm))

	}
}