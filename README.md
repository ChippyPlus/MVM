# MVM: A Micro Virtual Machine

Welcome to the MVM (Micro Virtual Machine) project! This is an experimental virtual machine written in Kotlin, designed
for educational purposes and to explore the fundamentals of virtual machine architecture.

**PLEASE LOOK**   
Keep in mind that this project is **STILL IN THE PROGRESS** and may parts are unfinished. I recommend you look into the
code yourself to see whats implemented.   
AND These docs are being changed. For instance some of the syscall/instruction tables are wrong because they are busy
being changed in production

## Project Overview

MVM is a simple, stack-based virtual machine that executes a custom assembly language. It features:

- **Custom Byte-code Language:**  A small, human-readable assembly language with instructions for data movement,
  arithmetic, stack operations, control flow, memory access, and system calls.
- **Fixed Memory:**  The VM uses a fixed amount of memory that is universally accessible to simplify memory management.
- **Error Handling:**  The VM uses exceptions to handle runtime errors, providing informative error messages.

## Documentation

For documentation please check the wiki tab!   
Over here → [wiki tab](https://github.com/ChippyPlus/MVM/wiki)!

## Features

- **Basic Instruction Set:** Includes instructions for data movement (`MOV`, `LIT`), arithmetic (`ADD`, `SUB`, `MUL`,
  `DIV`), stack operations (`PUSH`, `POP`, `PEEK`), control flow (`JMP`, `JZ`, `JNZ`), memory access (`LOAD`, `STORE`),
  system calls (`SYSCALL`), and others.
- **System Calls:**  The VM supports a limited set of system calls for basic interactions with the operating system.
- **Error Handling:** The VM uses custom exception types to handle runtime errors, providing specific error messages and
  exit codes.

## Getting Started

Look over here [Getting started](https://github.com/ChippyPlus/MVM/wiki/Getting-Started)!

## Example of the VM byte code

```assembly
// Example program to print the number 10
LIT G1 10       // Load the value 10 into register G1
PUSH G1         // Push G1 onto the stack
PRINTS          // Print what evers on the stack
```

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests if you find bugs, want to add features, or
have suggestions for improvements. 
