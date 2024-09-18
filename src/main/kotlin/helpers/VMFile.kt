package helpers

import java.io.File

/**
 * Represents a file within the virtual machine's environment.
 *
 * @property file The [File] object associated with the virtual machine file.
 */
data class VMFile(val file: File)
