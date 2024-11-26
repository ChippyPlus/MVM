# MVM Process Management

The MVM (Micro Virtual Machine) kernel manages the creation, execution, and termination of processes.
Processes run concurrently within the VM's emulated environment, using cooperative multitasking.
The kernel uses a round-robin scheduler.

## Process States

A process in the MVM can be in one of the following states:

- **RUNNING:** The process is currently executing instructions.
- **CANCELLED:** The process has terminated by another process.
- **PAUSED:** The process is paused by another process.

## Process Control Block (KProcess)

The kernel represents each process using a `KProcess` object.
This object acts as the process control block (PCB), storing all essential information about the process:

- **`id`:**  A unique integer identifier for the process.  (Assigned when a process is created).
- **`vm`:** An instance of the `Vm` class, representing the VM's state for this process.
  Each process gets its own VM
  object.
- **`file`:** A `File` object, the path to the program that the process runs.
- **`instructionMemory`:** A list of `InstructData` objects that represent the instructions in the program.
- **`ipcPermissions`:** A list of mailbox IDs for inter-process communication (IPC) that this process has access to.
- **`parent`:** The ID of the process's parent process (or `null` for the initial process).
- **`thread`:** The Kotlin `Thread` that executes instructions for the process.

This data is stored and managed by the kernel.

## Process Creation (`fork` and `spawn`)

The kernel provides two system calls for creating processes:

- **`fork()`:** Creates a new process, a copy of the current process.
  The child process has its own memory space,
  registers, and stack, but it starts with the same code as the parent.
  returns the child process's PID to
  the parent process and 0 to the child process.
  The `thread` is determined by the VM itself.
  The process is initialised at runtime in `KProcess.init`
- **`spawn(programPath.kar)`:** Creates a new process under the VM using the assembly code, using the file specified by `programPath.kar`.
  It will not inherit the
  state of the parent process.
  The process is added to the scheduler's ready queue.

## Process Termination (`HALT`)

HALT is added by the OS automatically and is inaccessible to the programmer.
A process terminates by calling the `HALT` key word.
The kernel will remove the process from the scheduler's ready queue.
This is different from the exit system call which exits the entire VM, working on a fix.



## Inter-Process Communication (IPC)

Inter-process communication is managed through mailboxes.
Each process has a list of mailbox IDs (`ipcPermissions`) which it has access to.
The OS manages mailboxes.
Processes communicate by sending (`send`) and receiving (`receive`) messages.
Processes can establish connections with other processes using the `link` system call.

This document provides an overview of process management in the MVM. For detailed information about the `KProcess` class
and system calls,
refer to other sections in this wiki.
Content_copy
Use code with caution.
Markdown