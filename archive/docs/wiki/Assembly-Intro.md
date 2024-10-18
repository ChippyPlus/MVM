# Introduction to MVM Assembly

This page provides a gentle introduction to the MVM (Micro Virtual Machine) assembly language. MVM assembly is a
low-level language designed for a simple, stack-based virtual machine. It allows you to write programs by providing a
set of instructions that manipulate data stored in registers, memory, and on the stack.

## Basic Concepts

* **Registers:** MVM has several types of registers, which are like small storage locations within the VM:
	* **General Purpose Registers (G0-G9):** Used for general computations and data storage.
	* **System Registers (S0-S3):** Used for system calls (interactions with the operating system).
	* **Return Registers (R0-R9):** Used to store the results of operations or function calls.
	* **Function Registers (F0-F9):** Specifically used for passing arguments to functions.
	* **Internal Function Registers (IF0-IF9):** Used internally by the standard library functions.

* **Memory:** MVM has a fixed amount of memory, which can be accessed using memory addresses. Each memory address holds
  a 64-bit integer.

* **Stack:** The stack is a LIFO (Last-In, First-Out) data structure used for temporary storage, passing arguments to
  functions, and storing return values.

* **Instructions:** Instructions tell the VM what operations to perform. Each instruction has a mnemonic (a short name,
  like `ADD` or `MOV`) and usually one or more operands (arguments).

## Example Program

Here's a simple MVM assembly program that adds two numbers and prints the result:

```assembly
LIT G1 5       // Load the literal value 5 into register G1
LIT G2 10      // Load 10 into G2
ADD G1 G2      // Add the values in G1 and G2. The result is stored in R4
PRINTR R4      // Print the value in R4 (the result of the addition)
```

**Explanation:**

1. `LIT G1 5`:  This instruction loads the literal value `5` into general-purpose register `G1`.
2. `LIT G2 10`: This loads `10` into register `G2`.
3. `ADD G1 G2`: This instruction adds the values in `G1` and `G2`. The result of the addition is automatically stored in
   the return register `R4`.
4. `PRINTR R4`: This instruction prints the decimal representation of the value currently stored in register `R4` (which
   is 15 in this case).

## Key Instruction Types

* **Data Transfer:**  `MOV`, `LIT`, `STORE`, `LOAD`, `PUSH`, `POP`, `PEEK`, `CPY`
* **Arithmetic:** `ADD`, `SUB`, `MUL`, `DIV`, `MOD`, `POW`, `INC`, `DEC`, `NEG`
* **Bitwise:**  `AND`, `OR`, `XOR`, `NOT`, `SHL`, `SHR`
* **Control Flow:** `JMP`, `JZ` (jump if zero), `JNZ` (jump if not zero), `CALL`, `RET`
* **String Operations:** `STR`, `STRLEN`, `STRCMP`, `STRCAT`, `STRCPY`
* **Input/Output:** `PRINTS`, `PRINTR`, `SYSCALL` (for system calls like `readIo`, `writeIo`)
* **Miscellaneous:** `INR`, `DEALLOC`, `HELP`

## Further Learning

See the following pages for more details:

* [Instruction Set](Instruction-Set): Detailed explanations of each instruction.
* [Registers](Registers):  Information about the different register types.
* [Memory and Addressing](Memory):  How to work with memory in MVM assembly.
* [System Calls](System-Calls):  How to interact with the operating system.
* [Standard Library](Standard-Library):  Learn about pre-defined functions available in MVM.
* [Subroutines (Functions)](Subroutines): How to define and use functions (subroutines) in your MVM code.

This introduction gives you a basic understanding of MVM assembly. Start with simple programs and gradually explore the
more advanced features as you become comfortable with the fundamentals.
