# MVM Kernel Scheduler

The MVM (Micro Virtual Machine) kernel uses a scheduler to manage the concurrent execution of multiple processes.
The scheduler employs a round-robin algorithm,
allocating a single instruction cycle to each process before switching to the next.
This approach prioritises simplicity and reliability over complex scheduling strategies.

## Round-Robin Scheduling

The round-robin algorithm is a simple and efficient scheduling technique, particularly well-suited to the MVM's design.
Each process in the ready queue is given a single instruction cycle to execute.
After the instruction completes, the scheduler selects the next process from the ready queue,
effectively creating a circular order of execution.

## Scheduler Data Structures

The scheduler uses the following data structures:

- **Ready Queue:** A queue (`LinkedList`) containing the PIDs of processes ready to run. Processes are added to the ready queue when they are created or become ready after being blocked.
- **Running Process:** A variable to store the PID of the currently running process.


## Scheduler Algorithm

The scheduler's algorithm is straightforward.
Its function (`eventLoop`) iterates through a list of processes (`keepPcs`),
executing a single instruction per process in a round-robin fashion:

1. **Get Next Process:** Retrieve the next process from the ready queue (using `getNextProcess()`).
2. **Load Process State:** Load the process's state (registers, program counter, etc.) from its PCB (`KProcess`).
3. **Execute Instruction:** Execute a single instruction.
4. **Save Process State:** Save the process's state back into the PCB using the kernels `snapShotManager`.
5. **Add to Ready Queue (if not finished):** Add the process to the back of the ready queue, unless the process has ended.

This is repeated until all processes in `keepPcs` are finished.

## Handling Process Termination

When a process terminates (e.g., by executing the `exit` system call or completing its instructions),
the scheduler removes it from the ready queue.

## Integration with the Kernel

The scheduler is integrated into the kernel (`os/OS.kt`).
The kernel is responsible for managing process creation, termination, and other tasks related to process management.
The scheduler runs continuously to ensure processes are able to execute their instructions.

## Limitations of Round-Robin

This scheduling model does not prioritise certain processes or consider their CPU usage.
It also does not support preemption, and processes are limited to executing a single instruction at a time.
A long-running process that does not perform system calls will not give up the processor.


This document provides an overview of the MVM kernel scheduler.
For more information on other kernel components, please consult other parts of this wiki.