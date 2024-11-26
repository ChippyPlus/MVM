# MVM Kernel Overview

The MVM (Micro Virtual Machine) kernel is a time-sharing kernel designed for educational purposes.
It manages multiple processes concurrently within the VM's emulated environment.
The kernel is implemented in Kotlin and runs separately from the VM's user-space processes;
user programs interact with the kernel via system calls.
The kernel itself does not reside in the VM's emulated memory.

## Key Kernel Components

The MVM kernel comprises the following key components:

### Scheduler

The scheduler manages the execution of processes,
allocating a single instruction cycle to each process before switching.
The MVM kernel uses a round-robin scheduler.
The scheduler maintains a ready queue of processes waiting for execution.
The scheduler is invoked after each instruction completes execution.

### Process Manager

This component is responsible for creating, managing, and terminating processes. Key data structures include:

- **Process Control Block (PCB):** Represented by the `KProcess`(Kernel Active Process) class, this data structure
  stores information about each process (PID, registers, memory, state, etc.).
- **System Calls:** The kernel provides system calls for process creation (`fork`, `spawn`), termination (`exit`), and
  other process-related operations.

### Snap Shot Manager

The snapshot manager keeps track of the state of each process at any given time during execution.
When the kernel switches between processes, it snapshots the state the active process and saved it in memory.
This includes

* Registers
* Memory
* Pc

### Virtual File System (VFS)

The VFS driver manages file system operations within the VM
and is responsible for implementing a set of system calls for file operations.
The VFS is persistent, saving and loading data from the `vfs.fs` file.

### Virtual Devices

The kernel includes drivers for virtual devices:

- **Console I/O:** Provides system calls for console input (`readIo`) and output (`writeIo`).
- **Timer:** The timer is not emulated as a virtual device.
  Instead, the scheduler is driven by a counter that
  increments after each instruction cycle.
  This is private to the VM itself

### Inter-Process Communication (IPC)

The kernel manages IPC between processes using message passing mailboxes.

### System Call Handler

This component intercepts system calls from user-space programs, dispatching them to the appropriate kernel functions.

## Execution Model

The MVM uses a round-robin scheduling model.
Each process receives a single instruction before the scheduler switches to another process.
Processes do not block on system calls; they execute one instruction at a time.
This design prioritises reliability and simplicity when handling many concurrent processes.

This document gives a high-level overview of the MVM kernel.
For details on specific components and their functions, consult other sections of this wiki.