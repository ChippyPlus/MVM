package internals.instructions.functions


@Deprecated("Use ExecuteLib", ReplaceWith("ExecuteLib()"))
fun Functions.call(functionName: String) {
	if (functionName !in functionsList) {
		error("\"$functionName\" is not a function")
	}

	throw NotImplementedError("Eh Deprecated!")
}