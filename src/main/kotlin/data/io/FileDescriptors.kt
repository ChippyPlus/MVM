package org.example.data.io

import org.example.helpers.VMFile
import java.io.File

class FileDescriptors {
    val fds = emptyMap<Int, VMFile>().toMutableMap()

    fun getFileDescriptor(fd: Int): VMFile? {
        return fds[fd]
    }

    fun addFileDescriptor(fileName: VMFile): Int {
        val fd = fds.size + 1
        fds[fd] = fileName
        return fd
    }

    fun removeFileDescriptor(fd: Int) {
        fds.remove(fd)
    }

}