package helpers

import internals.instructions.misc.HelpJsonEntire
import internals.instructions.misc.HelpJsonPartial
import kotlinx.serialization.json.Json
import java.io.File

fun gatherHelp(string: String): HelpJsonPartial {
	var usable: HelpJsonPartial? = null
	// TODO add help config here!!!!
	val f = Json.decodeFromString<HelpJsonEntire>(File("src/main/resources/help.jsonc").readText())
	for (i in f.stdlib) {
		if (i.name == string) {
			usable = i
			break
		}
	}
	for (i in f.instructions) {
		if (i.name == string) {
			usable = i
			break
		}
	}
	return usable!!
}
