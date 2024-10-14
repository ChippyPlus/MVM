# Architecture Overview

The MVM (Micro Virtual Machine) is a stack-based virtual machine designed for simplicity and educational purposes. This
page provides a high-level overview of the VM's architecture, including its key components and how they interact.

## Key Components

* **Memory:** A fixed-size linear array of memory locations. Each location stores a 64-bit integer. Memory is addressed
  using direct addressing (the address is specified directly in the instruction). Strings are stored as null-terminated
  character arrays. Arrays have a hybrid representation with size metadata followed by elements.

* **Registers:** MVM uses several types of registers:
	* General Purpose (G0-G9)
	* System (S0-S3)
	* Return Value (R0-R9)
	* Function Arguments (F0-F9)
	* Internal Function (IF0-IF9)
	  All registers are 64 bits wide.

* **Stack:** A LIFO (Last-In, First-Out) stack used for temporary storage, passing function arguments, and storing
  function return values. The `PUSH`, `POP`, and `PEEK` instructions operate on the stack.

* **Program Counter (PC):** A special register that holds the memory address (or in the interpreted version the line
  number in the code) of the currently executing instruction. The PC is incremented after each instruction (unless a
  jump or function call modifies it).

* **Instruction Set:** A set of instructions that the VM can execute. These instructions perform operations on
  registers, memory, and the stack. See the [Instruction Set](Instruction-Set) documentation for details.

* **Standard Library:** A collection of pre-defined functions implemented either in MVM assembly or Kotlin (using the
  `Klib` mechanism) to provide common functionalities like string manipulation, input/output, and basic math operations.
  See the [Standard Library](Standard-Library) documentation.

* **System Calls:** A mechanism for interacting with the host operating system (or environment). System calls are
  invoked using the `SYSCALL` instruction. See the [System Calls](System-Calls) documentation.

* **Execution Engine:** The part of the VM that fetches, decodes, and executes instructions. It manages the PC,
  registers, memory, and the stack.

## Execution Cycle

The MVM execution engine follows a fetch-decode-execute cycle:

1. **Fetch:** The instruction at the memory address pointed to by the PC is retrieved from memory.
2. **Decode:** The instruction is decoded to determine its type and operands.
3. **Execute:** The appropriate operation is performed based on the instruction and its operands. This may involve
   reading or writing registers, accessing memory, manipulating the stack, or invoking a system call.
4. **Increment PC:** The program counter is incremented to point to the next instruction in memory (unless the executed
   instruction was a jump or function call).

## Subroutine (Function) Calls

The `CALL` instruction is used to invoke subroutines (functions). Arguments are passed in the F registers, and the
return value (if any) is pushed onto the stack. The `RET` instruction returns control to the caller.

## Error Handling

Errors during program execution (e.g. invalid register access, memory violations, division by zero, system call errors)
cause exceptions, which immediately terminate the program. Specific error codes are used to indicate the type of error.
See the [Error Codes](Error-Codes) documentation.

