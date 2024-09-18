package data.io

import helpers.VMFile

/**
 * Manages file descriptors within the virtual machine.
 *
 * File descriptors are represented as longs and are associated with [VMFile] objects.
 */
class FileDescriptors {
    val fds = emptyMap<Long, VMFile>().toMutableMap()

    /**
     * Returns the [VMFile] associated with a given file descriptor.
     *
     * @param fd The file descriptor to retrieve.
     * @return The [VMFile] associated with the file descriptor, or `null` if not found.
     */
    fun getFileDescriptor(fd: Long): VMFile? {
        return fds[fd]
    }

    /**
     * Adds a file descriptor for the specified [VMFile] and returns the new file descriptor.
     *
     * @param fileName The [VMFile] to add.
     * @return The new file descriptor assigned to the [VMFile].
     */
    fun addFileDescriptor(fileName: VMFile): Long {
        val fd = fds.size + 1L
        fds[fd] = fileName
        return fd
    }
}