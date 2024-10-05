import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

val r4Outs = setOf("add", "sub", "mul", "div", "mod", "strlen", "strcmp", "strcat", "lt", "gt")
val r3Outs = setOf("and", "or", "not", "xor", "shr", "shl")

@OptIn(ExperimentalSerializationApi::class)
val json = Json {
	prettyPrint = true
	allowStructuredMapKeys = true
	allowComments = true
}