package internals.instructions.misc


import data.registers.IntelRegisters
import data.registers.intelNames
import helpers.gatherHelp
import helpers.toLong
import kotlinx.serialization.Serializable


fun Misc.help(registerString: String) {
	val i = vm.helpers.gatherHelp(registerString)
	println("Help prompt - \"${i.name}\"")
	for (j in i.arguments) {
		println("Argument - ${j.name}: ${j.info} ")
	}
	println("Description - ${i.info}")
	vm.registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

}


@Serializable
data class HelpJsonEntire(
	val stdlib: List<HelpJsonPartial>,
	val instructions: List<HelpJsonPartial>,
)

@Serializable
data class HelpJsonPartial(var name: String, val arguments: List<HelpJsonArguments>, val info: String)

@Serializable
data class HelpJsonArguments(val name: String, val info: String)
