package optimisations

import engine.parseEngine.TokenData

class Optimisations(val globalInfo: List<TokenData>) {
	fun optimise() {
		val swapWorseAlternative = SwapWorseAlternative(globalInfo)
	}
}