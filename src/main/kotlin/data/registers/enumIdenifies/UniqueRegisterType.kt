package data.registers.enumIdenifies

/**
 * Represents the unique single registers available in the virtual machine.
 * These registers are not part of the standard register groups (R, G, S, F).
 */
enum class UniqueRegisterType {
    /**
     * The Memory Base Register. Holds a base address for a specific memory region.
     */
    BASE,

    /**
     * The Memory Limit Register. Defines the upper bound of a memory region (used with BASE).
     */
    LIMIT,

    /**
     * The Status Register. Contains flags and bits representing the overall state of the VM.
     */
    STATUS,

    /**
     * The Temporary Register. Designated for holding intermediate values during calculations.
     */
    TEMP,

    /**
     * The Counter-Register. Designed for easy incrementing and decrementing, often used for loop counters.
     */
    COUNT
}
