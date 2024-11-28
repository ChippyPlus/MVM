# Introduction to MVM Assembly Language

This page provides a gentle introduction to the MVM (Micro Virtual Machine) assembly language. MVM assembly is a
low-level language designed for a simple, time-sharing virtual machine. It allows you to write programs by providing a
set of instructions that manipulate data stored in registers and memory. The virtual machine manages multiple processes
concurrently.

## Basic Concepts

* **Registers:** The MVM uses several types of registers:
	* **General Purpose Registers (G1-G10):** Used for general computations and data storage.
	* **System Registers (S1-S4):** Used for passing arguments to system calls.  `S1` holds the system call ID.
	* **Return Registers (R1-R10):** Used to store the results of operations and system calls.
	* **Function Argument Registers (F1-F10):** Used for passing arguments to functions.
	* **Floating-Point Registers (X1-X10):** Used for floating-point arithmetic.
	* **Intel Registers (I1-I10):** Hold status flags and other information.
* **Memory:** The MVM has a fixed amount of memory, accessed using addresses. Each address holds a 64-bit integer.
* **Stack:** A last-in, first-out (LIFO) data structure for temporary storage. Used for function calls return values.
* **Instructions:** Instructions specify operations. Each instruction has a mnemonic (e.g., `ADD`, `MOV`) and
  arguments (operands).
* **System Calls:** Functions to interact with the operating system (OS plus Kernel), such as file I/O, process
  management, and more. These are invoked using the `SYSCALL` instruction, which takes the system call ID as an
  argument.

## Example Program

This simple program adds two numbers and prints the result:

```assembly
LIT G1 10      // Load 10 into G1
LIT G2 5       // Load 5 into G2
ADD G1 G2 R1   // Add G1 and G2; result in R4
PRINTR R1      // Print the value in R4 (15)
```

## Key Instruction Categories

* **Data Transfer:** `LIT`, `XLIT`, `MOV`, `STORE`, `LOAD`, `PUSH`, `POP`, `PEEK`, `CPY`, `SETTYPE`, `DEALLOC`
* **Arithmetic:** `ADD`, `SUB`, `MUL`, `DIV`, `MOD`, `POW`,
* **Bitwise:** `AND`, `OR`, `XOR`, `NOT`, `SHL`, `SHR`
* **XFloats:** `XADD`, `XSUB`, `XMUL`, `XDIV`, `XMOD`, `XPOW`, `ITOF`,    `FTOI`
* **Control Flow:** `JMP`, `JZ`, `JNZ`, `CALL`, `RET`
* **String:** `STR`
* **I/O:** `PRINTS`, `PRINTR`
* **System Calls:** `SYSCALL`
* **Miscellaneous:** `INR`, `HELP`, `SLEEP`

## Further Learning

For detailed information, refer to:

* [Instruction Set](Instruction-Set.md): Detailed explanation of each instruction.
* [Registers](Registers.md): Information on the different register types.
* [Memory Model](Memory-Model.md): How memory is organised and accessed.
* [System Calls](System-Calls.md): How to interact with the OS kernel.
* [Standard Library](Standard-Library.md): Overview of available functions.

This introduction provides a foundation for understanding the MVM assembly language. You can start writing programs
after
understanding the basic concepts and then explore more advanced features.


