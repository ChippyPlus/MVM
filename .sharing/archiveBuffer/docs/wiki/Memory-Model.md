## Memory Model in MVM

This document describes the memory model and stack implementation used in the Micro Virtual Machine (MVM).

### Overview

MVM utilizes a flat, linear memory model with a fixed size, along with a stack for function calls and temporary data
storage.

* **Flat Memory:** All memory locations are directly addressable using integer addresses, with no segmentation or
  paging.
* **Fixed Size:** The total memory available to the VM is predetermined (currently 64 words), defined by the
  `MEMORY_LIMIT` constant.
* **Zero-Based Indexing:**  Memory addresses start from 0. The first word is at address 0, the second at address 1, and
  so on.

### Data Storage

* **Words:** The fundamental unit of memory is a word, represented as a 64-bit signed integer.
* **Data Types:** MVM's memory can store:
	- **Integers:**  Stored directly as 64-bit words.
	- **Characters:** Represented using their ASCII codes, with one word per character. Not ASCII-Extended
	- **Strings:**  Stored as null-terminated sequences of ASCII characters in consecutive memory locations.

### Memory Access Instructions

* **`LOAD`:**  Loads a value from a memory address (specified in Argument 1) into a register (Argument 2).
* **`STORE`:** Stores the value from a source register (Argument 1) into the memory address held in the destination
  register (Argument 2).

**Example:**

```assembly
LIT G1 100		// Load the value 100 into register G1
STORE G1 20		// Store the value in G1 (100) into memory address 20
LOAD 20 G2		// Load the value from memory address 20 into register G2
```

### String Storage

Strings in MVM are represented as null-terminated arrays of characters.

- **Null Termination:**  A byte with the value 0 (null character) marks the end of the string. This allows determining
  the string's length dynamically.
- **Consecutive Storage:**  String characters are stored in consecutive memory words, each word holding one ASCII
  character code.

**Example:**

```assembly
// Store the string "MVM" in memory, starting at address 50

LIT G1 77      // ASCII code for 'M'
STORE G1 50    // Store 'M' at address 50

LIT G1 86      // ASCII code for 'V'
STORE G1 51    // Store 'V' at address 51

LIT G1 77      // ASCII code for 'M'
STORE G1 52    // Store 'M' at address 52

LIT G1 0       // Null terminator
STORE G1 53    // Store null terminator at address 53 
```

Or use `STR`. With this method the `STR` command will pick the closest available address to 0. Therefor you cannot
choose
your memory location. We just use `STORE` here for demonstration

### The Stack

MVM uses a stack data structure for:

* **Temporary Storage:** Providing space for intermediate values during complex calculations.

- **Stack Pointer:** The `stackOperations` component in the VM manages the stack.
- **Push and Pop:** The `PUSH`, `POP` and `PEEK` instructions are used to add values to and remove values from the
  stack, and view
  respectively.
- **Stack Limit:**  The stack has a fixed size (currently 32 words), set by the `STACK_LIMIT` constant.

**Example:**

```assembly
LIT G1 25		// Load 25 into G1
PUSH G1			// Push the value of G1 (25) onto the stack
PEEK G2			// Peek at the stack and load it into G2
POP G3			// Pop the top value from the stack into G3
```

### Memory Management

* **Static Allocation:** MVM currently uses static memory allocation. All memory is pre-allocated at VM startup.
* **No Dynamic Allocation:** There is no facility for dynamic memory allocation (e.g., a `malloc`-like instruction) at
  this time.

### Error Handling

* **Invalid Memory Access:**  The VM performs bounds checking to prevent accessing memory outside the allocated range.
  An `InvalidMemoryAddressException` is thrown if such access occurs.
* **Stack Overflow:**  If an attempt is made to push onto a full stack, a `StackOverflowException` will be thrown.
* **Stack Underflow:**  If a `POP` instruction is executed on an empty stack, a `EmptyStackException` will be thrown.

### Key Points

* **Simplicity:**  The flat memory model simplifies memory management but can be limiting for larger programs.
* **Stack Importance:** The stack is crucial for function calls (when implemented) and managing temporary data.
* **Error Prevention:**  Bounds checking and stack overflow/underflow detection help to ensure program stability.
