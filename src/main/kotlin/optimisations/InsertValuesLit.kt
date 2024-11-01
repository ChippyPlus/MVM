package optimisations

import data.registers.RegisterType
import engine.parseEngine.InstructType
import engine.parseEngine.TokenData


fun Optimisations.insertValuesLit(): List<TokenData> {
	val newT = mutableListOf<TokenData>()
	val valuesOfRegisters = mutableMapOf<RegisterType, Long>()
	globalInfo.forEach {
		if (it.name == "lit") valuesOfRegisters[it.values[0] as RegisterType] = it.values[1] as Long
	}

	for (i in globalInfo) {
		if (i.type == InstructType.RegisterRegister && i.values[0] as RegisterType in valuesOfRegisters.keys && i.values[1] as RegisterType in valuesOfRegisters.keys) {
			newT.add(
				TokenData(
					i.name, i.type, arrayOf(
						valuesOfRegisters[i.values[0] as RegisterType]!!,
						valuesOfRegisters[i.values[1] as RegisterType] as Any
					)
				)
			)
		} else newT.add(i)
	}
	return newT
}