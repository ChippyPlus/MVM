package internals.instructions.misc


import helpers.gatherHelp
import kotlinx.serialization.Serializable


fun Misc.help(registerString: String) {
	val i = gatherHelp(registerString)
	println("Help prompt - \"${i.name}\"")
	for (j in i.arguments) {
		println("Argument - ${j.name}: ${j.info} ")
	}
	println("Description - ${i.info}")
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
