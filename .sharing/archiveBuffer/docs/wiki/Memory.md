# Memory and Addressing

MVM (Micro Virtual Machine) has a fixed-size memory space that can be used to store data. This page explains how memory
is organized, how to access it using addresses, and important concepts related to memory management in MVM assembly.

## Memory Organisation

MVM's memory is a linear sequence of memory locations (or addresses). Each memory location can hold a 64-bit integer
value. The total size of the memory is determined by the `MEMORY_LIMIT` configuration setting. Valid memory addresses
range from 0 to `MEMORY_LIMIT - 1`.

## Addressing Modes

MVM primarily uses direct addressing to access memory. This means that the operand of a memory access instruction (like
`LOAD` or `STORE`) specifies the actual memory address.

## Memory Access Instructions

* **`STORE register1 register2`:** Stores the value from `register1` into the memory location whose address is held in
  `register2`.  `register2` acts as a pointer to a memory location.

	* Example:
    ```assembly
    LIT G1 100       // Value to store
    LIT G2 50        // Memory address to store at
    STORE G1 G2      // Store the value 100 at memory address 50
    ```

* **`LOAD register1 register2`:** Loads the value from the memory location at the address in `register1` into
  `register2`.

	* Example:
    ```assembly
    LIT G1 50        // Memory address to load from
    LOAD G1 G2      // Load the value from memory address 50 into G2
    ```

## String Storage

Strings in MVM are stored as null-terminated arrays of characters in memory. Each character is represented by its ASCII
value (a 64-bit integer). The `STR` instruction allocates memory for a string literal and stores its address in a
register.

## Array Storage

MVM can handle arrays using a hybrid approach:  The array's size (number of elements) is stored at the starting address
of the array's memory block (the "metadata"). The elements of the array are stored in contiguous memory locations
immediately following the metadata.

* **`createArray(size)` System Call:** Allocates memory for an array of the given `size` and returns its base address.
  The first memory location at this base address will contain the array's length, followed by its elements.
* **`arrayGet`, `arraySet` System Calls:** Used to access and modify array elements using the array's base address and
  the element's index. These calls handle bounds checking to prevent accessing invalid memory locations.

## Memory Management

MVM currently uses a fixed size memory. It is recommended that you clear strings after use to prevent memory leaks. This
can be done with the `DEALLOC` instruction. When implementing dynamic sized arrays (if/when you do). It is highly
recommended to implement automatic garbage collection or you will run into a ton of problems.

## Example: Array Access

```assembly
// Create an array of size 10
LIT F1 10          // Array size in S1
call arrays.create    // Create the array (System call ID determined by your implementation)

// Store the value 50 at index smallest index

PEEK F1        // Put the array address from the stack into F1
LIT F2 50       // Value to store
CALL array.append // store value (with index offset and base address)

LIT F2 0
CALL array.get
prints         

```

This example demonstrates how to create, store, and load array elements. See the [System Calls](System-Calls)
and [Standard Library](Standard-Library) documentation for details on `arrays.create`, `arrays.get` and `arrays.append`.

Understanding these concepts is crucial for writing correct and efficient MVM programs,
especially when working with strings and arrays.
Remember to consult the [Instruction Set](Instruction-Set) and system call documentation for the specifics
of how instructions and
system calls interact with memory.


