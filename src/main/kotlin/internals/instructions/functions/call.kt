package internals.instructions.functions

fun Functions.call(functionName: String) {
	if (functionName !in functionsList) {
		error("\"$functionName\" is not a function")
	}

}