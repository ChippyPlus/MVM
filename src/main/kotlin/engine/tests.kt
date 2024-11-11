package engine

import data.registers.RegisterType
import engine.execution.InstructData


val p1 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 20L)),
	InstructData("lit", arrayOf(RegisterType.G2, 30L)),
	InstructData("add", arrayOf(RegisterType.G1, RegisterType.G2)),
	InstructData("printr", arrayOf(RegisterType.R4))
)

val p2 = listOf(
	InstructData("lit", arrayOf(RegisterType.G1, 11L)),
	InstructData("lit", arrayOf(RegisterType.G2, 0L)),
	InstructData("store", arrayOf(RegisterType.G1, RegisterType.G2)),
	InstructData("load", arrayOf(RegisterType.G2, RegisterType.R3)),
	InstructData("printr", arrayOf(RegisterType.R3))
)