package helpers

import data.memory.MemoryAddress

fun String.toMemoryAddress(): MemoryAddress {
    return MemoryAddress(this.toLong())
}