package mvm.routes

import kotlinx.serialization.Serializable
import mvm.ArgumentsFormat
import mvm.HelpFormat
import mvm.PackageFormat


@Serializable
data class RequestFormat(
	val repository: String,
	val module: String,
	val `package`: PackageFormat,
)


data class RequestFormat2(
	val repository: String,
	val module: String,
	val `package`: PackageFormat,
)

@Serializable
val pRequestFormat = RequestFormat(
	repository = "kar-main", module = "stdlib", `package` = PackageFormat(
		name = "println",
		version = "1.0",
		code = "LIT S0 24\nMOV F1 S1\nSTRLEN S1\nADD R4 F1\nLIT G1 10\nLIT G2 0\nLIT G3 1\nSTORE G1 R4\nADD G3 R4\nSTORE G2 R4\nSYSCALL",
		help = HelpFormat(
			name = "println",
			arguments = listOf(ArgumentsFormat(name = "F1", info = "The string to print to the screen")),
			info = "Writes a string to the screen with a new line"
		),
		dependencies = listOf()
	)
)


val pRequestFormat2 = RequestFormat2(
	repository = "kar-main", module = "stdlib", `package` = PackageFormat(
		name = "println",
		version = "1.0",
		code = "LIT S0 24\nMOV F1 S1\nSTRLEN S1\nADD R4 F1\nLIT G1 10\nLIT G2 0\nLIT G3 1\nSTORE G1 R4\nADD G3 R4\nSTORE G2 R4\nSYSCALL",
		help = HelpFormat(
			name = "println",
			arguments = listOf(ArgumentsFormat(name = "F1", info = "The string to print to the screen")),
			info = "Writes a string to the screen with a new line"
		),
		dependencies = listOf()
	)
)

val packageRequestFormat = """
	{
                        "name": "println",
                        "version": "1.0",
                        "code": "LIT S0 24\nMOV F1 S1\nSTRLEN S1\nADD R4 F1\nLIT G1 10\nLIT G2 0\nLIT G3 1\nSTORE G1 R4\nADD G3 R4\nSTORE G2 R4\nSYSCALL",
                        "help": {
                            "name": "println",
                            "arguments": [
                                {
                                    "name": "F1",
                                    "info": "The string to print to the screen"
                                }
                            ],
                            "info": "Writes a string to the screen with a new line"
                        },
                        "dependencies": []
                    }
""".trimIndent()