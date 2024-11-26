# MVM Memory Model

The MVM (Micro Virtual Machine) uses a linear memory model with a fixed size.  All memory locations are directly addressable using 64-bit addresses. The MVM's memory is a single contiguous block.

## Memory Addressing

The MVM uses direct addressing. The operand of a memory access instruction (`LOAD`, `STORE`) specifies the memory address directly.  These are *physical addresses* within the VM's single memory block. There is no virtual addressing or memory segmentation in this model.

The MVM performs boundary checks when accessing memory.  Attempting to read from or write to a memory address outside the allocated range for the current process will raise an exception. 

```assembly
lit G1 100       // Value to store
lit G2 50        // Memory address 
store G1 G2      // Store 100 at address 50
```

## String Storage

Strings are stored as null-terminated arrays of 64-bit words. Each word holds one character (ASCII). The null terminator (value 0) marks the end of the string.  Memory for strings is allocated dynamically.

```assembly
str G1 "Hello"   ; Store "Hello"; G1 contains string's starting address
```
This looks like `104 | 101 | 108 | 108 | 111 | 0 ` in memory

## Array Storage

Arrays are stored contiguously in memory. The first word contains the array's size (number of elements). Subsequent words hold array elements.  The data type of array elements is specified during array creation using the `createArray` system call.


```assembly
// Create array of size 10, Longs
lit S1 26      // Array Create
lit S2 10       // Size of 10
syscall        // Result: Array base address in R2

// Store value 50 at index 0
lit S1 27       // Array Set
mov R2 S2 		// Move Array to S2
lit S3 0		// Index 0
lit S4 50		// Value 50
syscall         // arraySet
```

This document explains the MVM's linear memory model, how memory is addressed, and how strings and arrays are stored. The MVM uses direct addressing and allocates memory for strings dynamically.


