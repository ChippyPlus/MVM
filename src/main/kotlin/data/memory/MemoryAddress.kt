package data.memory

/**
 * Represents a memory address in the virtual machine.
 * @property address The numerical value of the memory address, or `null` if the address is not initialised.
 */
@JvmInline
value class MemoryAddress(val address: Long?)