package data.memory


/**
 * Represents a value stored in a memory address within the virtual machine.
 * @property value The numerical value stored at the memory address, which is a 64-bit signed integer (Long).
 *                 A value of `null` indicates that the memory address has not been initialised.
 */
@JvmInline
value class MemoryValue(val value: Number?)