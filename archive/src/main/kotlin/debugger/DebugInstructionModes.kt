package debugger

/**
 * Represents the different modes in which debug instructions can be executed.
 */
enum class DebugInstructionModes {
	/**
	 * Debug instructions executed at specific lines of code, as defined in the `lineSpecific` map of the [DebugFile].
	 */
	Line,

	/**
	 * Debug instructions executed at each iteration of the VM's execution loop, as defined in the `eachIteration` list of the [DebugFile].
	 */
	Iterator
}