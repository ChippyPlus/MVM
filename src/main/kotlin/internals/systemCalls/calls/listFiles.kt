package internals.systemCalls.calls

import internals.systemCalls.SystemCall


fun SystemCall.listFiles() = call("listFiles") {
	throw NotImplementedError("Idk how to store stings in arrays therefor not happening")
}