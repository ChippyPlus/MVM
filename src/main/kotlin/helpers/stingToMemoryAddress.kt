package org.example.helpers

import org.example.data.memory.MemoryAddress

fun String.toMemoryAddress(): MemoryAddress {
    return MemoryAddress(this.toInt())
}