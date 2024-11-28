# Micro Virtual Machine (MVM)

**A Kotlin-based Educational Virtual Machine**

This wiki provides comprehensive documentation for the Micro Virtual Machine (MVM) project, an educational virtual
machine designed to explore the fundamentals of operating system and virtual machine architecture.

## Project Overview

The MVM project aims to create a flexible and extensible virtual machine implemented in Kotlin. It serves as a platform
for learning and experimenting with low-level programming concepts within a higher-level language environment. This
rewrite focuses on simulating a time-sharing operating system kernel within the virtual machine.

## Key Features

* **Custom Assembly Language:** The virtual machine uses a straightforward assembly language for writing programs.
  Instructions support data movement, arithmetic, stack operations, control flow, memory access, and system calls.
* **Dynamic Register Sizing:** Registers can have their data type changed at runtime, providing flexibility and memory
  efficiency.
* **Multi-Process Support:** The virtual machine can run multiple programs concurrently as distinct processes,
  simulating a time-sharing OS on a single (emulated) core.
* **Virtual File System (VFS):** A virtual file system enables programs to interact with files and directories within
  the VM.
* **Inter-Process Communication (IPC):** Provides mechanisms (message passing) for processes to communicate with each
  other.
* **Robust Error Handling:** The virtual machine uses a dedicated set of Intel registers to manage error conditions.
  This provides informative error messages and the ability to handle errors gracefully, without halting the entire
  system.
* **Extensible Design:** The modular design of the project makes it easier to add new features, instructions, and
  standard library functions.

## Getting Started

For detailed instructions on setting up and running the virtual machine, please refer to
the [Installation and Setup](Getting-Started.md) guide.

## Explore the Documentation

The MVM's documentation is organised to guide you from basic concepts to more advanced topics. Please consult the
sidebar for quick navigation. The documentation is intended to serve as a comprehensive reference for the project's
design and implementation details.