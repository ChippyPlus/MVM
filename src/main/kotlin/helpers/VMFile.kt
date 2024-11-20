package helpers

import java.io.File

/**
 * Represents a file within the virtual machine's environment.
 *
 * @property file The [File] object associated with the virtual machine file.
 */
@Deprecated("We have moved to Ventry. But its not in this branch soooooo", replaceWith = ReplaceWith("Ventry"))
data class VMFile(val file: File)

