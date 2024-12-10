# MVM: A Micro Virtual Machine and Operating System Kernel in Kotlin

Welcome to the MVM (Micro Virtual Machine) project!
This is an experimental virtual machine and operating system kernel
written in Kotlin, designed for educational purposes and to explore the fundamentals of computer architecture, operating
systems, and virtual machine design.
MVM provides a hands-on learning experience for understanding how operating
systems, byte code, interpreters, kernels, and virtual machines work.

## Project Overview

MVM is a stack and register-based virtual machine that executes a custom assembly language.
It features a simple, yet
powerful kernel
written in Kotlin, providing system calls, process management, a virtual file system, and drivers for virtual devices.
This project is ideal for students, hobbyists, and anyone interested in learning about low-level programming and
operating system concepts in a higher-level language environment.

## Features

* **Custom Assembly Language and Bytecode Interpreter:** MVM uses a human-readable assembly language with a custom
  bytecode format.
  A bytecode interpreter executes instructions efficiently.
* **Educational Focus:** Designed specifically for learning.
  Explore concepts like memory management, system calls,
  drivers, and process scheduling.
* **Kotlin Implementation:** Leveraging Kotlin's modern features for cleaner code and easier extensibility.
* **Multitasking Kernel:** The MVM kernel supports multiple processes through a time-sharing, round-robin scheduler.
* **Virtual File System (VFS):** A basic VFS allows programs to create, read, write, and delete files within the
  virtual machine.
* **Virtual Devices and Drivers:** Create and interact with virtual devices through a driver interface.
* **Built-in Standard Library:** Provides helpful functions for common tasks like string manipulation, I/O, and
  conversions.
* **Robust Error Handling:**  Clear error messages and exception handling to aid in debugging.

## Getting Started

Learn how to build, run, and experiment with the
MVM: [Getting Started Guide](https://github.com/ChippyPlus/MVM/wiki/Getting-Started)

## Example Assembly Code

```assembly
// Prints "Hello, World!"
STR F1 "Hello, World!"  // Store string in memory, address in F1
call io.println  // Use the standard library print function
```

## Contributing

Contributions, bug reports, and feature suggestions are welcome!
This is an educational project, and we encourage
collaboration.
If you have an idea, want to fix a bug or implement a new feature (like a JIT compiler, garbage
collection, or more advanced system calls!), open an issue or submit a pull request.
We appreciate your support!
