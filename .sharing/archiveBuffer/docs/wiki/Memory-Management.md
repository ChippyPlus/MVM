## Memory Management

This document describes the memory management strategy employed by the Micro Virtual Machine (MVM).

### Overview

MVM uses a simple, static memory allocation scheme. This means that the VM has a fixed amount of memory that is
allocated when the VM starts. There is no dynamic allocation or deallocation of memory during program execution.

### Key Features

* **Fixed-Size Memory:** The VM has a predetermined amount of memory (1024 words).
* **Universal Accessibility:** All memory locations are directly addressable and accessible to guest programs.
* **No Dynamic Allocation:** The VM does not provide a mechanism for programs to dynamically request or release memory
  blocks during execution.

### Advantages

* **Simplicity:** The memory management scheme is very straightforward. There is no need for complex data structures or
  algorithms to manage memory allocation.
* **Performance:** Memory access is fast and efficient because there is no overhead associated with dynamic allocation.

### Disadvantages

* **Limited Memory Space:** The fixed memory size limits the size of programs that can be executed in the VM and the
  amount of data that can be stored.
* **Potential Fragmentation:** If programs use memory in a non-contiguous way, unused memory slots might become
  scattered throughout the memory space, potentially making it impossible to allocate larger blocks, even if there is
  enough free memory overall. This is called fragmentation.
* **Security:** Because all programs have access to the entire memory space, there is no memory isolation between
  programs. This could pose a security risk if multiple programs are running in the VM.

### Addressing

* **Addresses:** Memory locations are addressed using integers, starting from 0 to 1024.
* **Word Size:** The basic unit of memory is a 32-bit word.

### Data Types

MVM's memory can store various data types, but they’re all represented as integers:

* **Integers:** Stored directly as 32-bit values.
* **Characters:** Represented using their ASCII codes, stored as 32-bit integers.
* **Strings:** Stored as null-terminated sequences of characters, with each character represented by its ASCII code (as
  a 32-bit integer).

### Instructions

* **`LOAD`:** Loads a value from a specified memory address into a register.
* **`STORE`:**  Stores a value from a register into a specified memory address.

### Error Handling

* **Invalid Memory Address:** If a program attempts to access a memory address outside the bounds of the allocated
  memory space, the VM throws an `InvalidMemoryAddressException`.

### Example

```
// Store the value 10 at memory address 50
LIT G1 10
STORE G1 50 

// Load the value from memory address 50 into register G2
LOAD 50 G2
```

### Future Considerations

Maybe

* **Dynamic Allocation:** If you decide to add dynamic memory allocation (`ALLOC` instruction) to your VM in the future,
  you'll need to implement a memory manager to handle allocation, deallocation, and potentially memory compaction to
  reduce fragmentation.
* **Memory Protection:** If security is a concern, you could explore adding memory protection mechanisms to isolate
  programs from each other. This would involve more complex memory management and access control.

