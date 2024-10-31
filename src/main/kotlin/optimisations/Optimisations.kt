package optimisations

import engine.execution.InstructData

class Optimisations(val globalInfo: List<InstructData>) {
	fun optimise() {
		val swapWorseAlternative = SwapWorseAlternative(globalInfo)
	}
}