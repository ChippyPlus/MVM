package org.example.data.io

import java.io.File

class FileDescriptors {
    val fds = emptyMap<Int, File>().toMutableMap()

    fun addFileDescriptor(fileName: File): Int {
        val fd = fds.size + 1
        fds[fd] = fileName
        return fd
    }

    fun removeFileDescriptor(fd: Int) {
        fds.remove(fd)
    }

}