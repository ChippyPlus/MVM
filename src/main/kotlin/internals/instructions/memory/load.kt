package org.example.kvmInternals.instructions.memory

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite
import org.example.internalMemory

/**
 * Loads a value from the internal memory at the specified [memoryAddress] and writes it
 * to the specified [destination] register.
 *
 * @param memoryAddress The address in memory to read the value from.
 * @param destination The register type to write the loaded value to.
 */
fun Memory.load(memoryAddress: MemoryAddress, destination: SuperRegisterType) {
    fullRegisterWrite(destination, internalMemory.read(memoryAddress).value!!.toLong())
}