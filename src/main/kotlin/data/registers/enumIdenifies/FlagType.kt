package data.registers.enumIdenifies

/**
 * Represents the various status flags used by the virtual machine.
 */
enum class FlagType {
    /**
     * Set if the result of the last arithmetic or logical operation was zero.
     */
    ZF,

    /**
     * Carry Flag.
     * - Set if an addition operation results in a carry (unsigned overflow).
     * - Set if a subtraction operation requires a borrow (unsigned underflow).
     */
    CF,

    /**
     * Set if the last arithmetic operation resulted in a signed overflow.
     */
    OF,

    /**
     * Set if the last string comparison operation found that the two strings were equal.
     */
    SEQ,

    /**
     * Set if the last string comparison operation found that the first string is lexicographically greater than the second string.
     */
    SGT,

    /**
     * Set if the last `openFile` system call successfully opened a file.
     */
    FOF,

    /**
     * Set if the last memory allocation operation was successful. Cleared if the allocation failed (out of memory).
     */
    MAF,

    /**
     * Set if the last input/output system call resulted in an error.
     */
    IOF


}