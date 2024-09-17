package data.io

import helpers.VMFile

class FileDescriptors {
    val fds = emptyMap<Long, VMFile>().toMutableMap()

    fun getFileDescriptor(fd: Long): VMFile? {
        return fds[fd]
    }

    fun addFileDescriptor(fileName: VMFile): Long {
        val fd = fds.size + 1L
        fds[fd] = fileName
        return fd
    }

    fun removeFileDescriptor(fd: Long) {
        fds.remove(fd)
    }

}