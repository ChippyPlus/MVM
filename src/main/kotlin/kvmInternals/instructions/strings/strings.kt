package org.example.kvmInternals.instructions.strings


/**
 * These instructions operate on strings stored in memory,
 * using memory addresses stored in registers.
 * * `STR` - stores a string literal in memory and the start of it into a register
 * * `STRCPY` - Copies a string from one memory location to another
 * * `STRLEN` - Calculates the length of a string and stores it in a destination register
 */
open class Strings