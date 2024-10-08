package internals.instructions.misc


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

fun Misc.help(registerString: String) {
	val f = Json.decodeFromString<HelpJsonEntire>(File("src/main/resources/help.jsonc").readText())
	val wanted = registerString
	for (i in f.stdlib) {
		if (i.name == wanted) {
			println("Help prompt - \"${i.name}\"")
			for (j in i.arguments) {
				println("Argument - ${j.name}: ${j.info} ")
			}
			println("Description - ${i.info}")
		}
	}
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
