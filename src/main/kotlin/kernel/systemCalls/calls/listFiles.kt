package kernel.systemCalls.calls

import kernel.systemCalls.SystemCall


fun SystemCall.listFiles() = call("listFiles") {
	throw NotImplementedError("Idk how to store stings in arrays therefor not happening")
}