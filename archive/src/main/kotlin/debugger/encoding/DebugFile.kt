package debugger.encoding

import kotlinx.serialization.Serializable

/**
 * Represents the configuration for the debugger, loaded from a JSON file.
 *
 * @property lineSpecific A map of line numbers (as strings) to debug instructions that should be executed at those specific lines.
 * @property eachIteration A list of debug instructions that should be executed at each iteration of the VM's execution loop.
 */
@Serializable
data class DebugFile(
	val lineSpecific: Map<String, String>, val eachIteration: List<String>,
)