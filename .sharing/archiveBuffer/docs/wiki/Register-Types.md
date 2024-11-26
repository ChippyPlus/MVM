## Register Types in MVM

This document describes the different types of registers used in the MVM (Micro Virtual Machine) architecture, their
purposes, and how they are used in assembly programming.

### Overview

Registers are small, fast memory locations within the VM that hold data during program execution. MVM uses a simple
register model with three types of registers:

* **General-Purpose Registers:** For general computations and data manipulation.
* **System Registers:**  Specifically used for system calls.
* **Return Registers:** Primarily used to store results of operations and system calls.

### General-Purpose Registers

* **Mnemonic Prefix:** `G`
* **Registers:** `G1`, `G2`, `G3`, `G4`
* **Purpose:** These registers are the workhorses of MVM. They are used in arithmetic, logical, and data movement
  instructions. You can load data into them from memory, use them in calculations, and store their values back to
  memory.

**Example Usage:**

```assembly
LIT G1 10      // Load the literal value 10 into register G1
MOV G2 G1      // Copy the value from G1 into G2
ADD G1 G2      // Add G1 and G2, store the result in R4 (most arithmetic instructions use R4 for results)
```

### System Registers

* **Mnemonic Prefix:** `S`
* **Registers:** `S1`, `S2`, `S3`, `S4`
* **Purpose:** These registers used for `syscalls` by the VM
	* **`S1`:** Holds the system call number, identifying which system call to execute.
	* **`S2`, `S3`, `S4`:** Used for passing arguments to the system call.

**Example Usage:**

```assembly
// System call to print a string to the console 

LIT S1 24        // Load system call number 24 (writeIo) into S1
STR G1 "Hello"  // Store the string "Hello" in memory and put its address into G1
MOV S2 G1        // Move the string address from G1 to S2 (argument for writeIo)
SYSCALL          // Execute the system call 
```

### Return Registers

* **Mnemonic Prefix:** `R`
* **Registers:** `R1`, `R2`, `R3`, `R4`
* **Purpose:** These registers are primarily used to store results from operations, especially system calls. Many
  instructions and system calls automatically store their outputs in these registers.

**Example Usage:**

For R2, system calls

```assembly
LIT S1 14	// Loads 14, the syscall to get the time
SYSCALL		// Executes syscall and store the time into R2
PRINTR R2 	// Prints the current time from R2
```

For R3, Bitwise instructions

```assembly
LIT G1 9	// Load 9 into G1
NOT G1		// Apply the not operation and store result in R3
PRINTR R3	// Prints the not of the Long of 9. -10
```

For R4, arithmetic instructions

```assembly
LIT G1 10     	// Load 10 into G1
LIT G2 5      	// Load 5 into G2
ADD G1 G2     	// Add G1 and G2, the result is stored in R4 automatically
MOV G3 R4     	// Move the result from R4 into G3
PRINTR R4		// Print 15
```

### Key Points

* **Register Size:** All registers in MVM currently hold 64-bit integer values.
* **Dedicated Use:**  While system and return registers *can* be used for general computations, it's best practice to
  stick to their intended purposes for clarity and to avoid unexpected behavior.
* **Implicit Result Storage:** Many arithmetic and string operations automatically store their results in `R4`. System
  calls commonly use `R2` for return values.


