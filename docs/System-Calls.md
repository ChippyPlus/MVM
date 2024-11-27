# MVM System Calls Overview

System calls provide a mechanism for MVM (Micro Virtual Machine) programs to request services from the operating system
(kernel).
All system calls use the stack for arguments and return values.
The system call ID is passed in register `S1`.

## System Call Categories

The system calls are categorised for clarity:

### File System Calls

These system calls interact with the virtual file system (VFS):

- `newFile`: Creates a new file.
- `readFile`: Reads data from a file.
- `writeFile`: Writes data to a file.
- `deleteFile`: Deletes a file or directory.

### Process Management Calls

**Very UNSTABLE!**

These system calls manage processes within the MVM:

- `exec`: Executes a new program from the host operating system.
- `fork`: Creates a child process (a copy of the current process).
- `spawn`: Creates a new process and runs a program file.
- `exit`: Terminates the current process.
- `getpid`: Gets the process ID of the current process.
- `getuid`: Gets the user ID of the current process.
- `handleSignals`: Registers a signal handler for the process.
- `sendSignal`: Sends a signal to another process.
- `pause_t`: Pauses a specified process.
- `continue_t`: Resumes a paused process.

### IPC Calls

**Very UNSTABLE!**
These system calls provide inter-process communication (IPC):

- `share_m`: Establishes a shared memory region between two processes.
- `send`: Sends a message to another process using message passing.
- `receive`: Receives a message from another process.

### Host OS Calls

These system calls interact with the host operating system:

- `time`: Gets the current system time.

### Other System Calls

These system calls provide other miscellaneous functionality:

- `writeIo`: Writes a null-terminated string to standard output.
- `readIo`: Reads a line from standard input.
- `createArray`: Creates a new array.
- `arraySet`: Sets an array element.
- `arrayGet`: Gets an array element.

## Making System Calls

System calls are invoked using the `syscall` instruction.
The system call ID (`SystemCallID`) is in register `S1`.
Arguments are passed in `S2`, `S3`, and `S4`.
Return values (if any) are stored in `R2`

```assembly
lit S1 14        // Load system call ID 14 (time) into S1
syscall              // Make the system call
```

## Further Information

For a detailed list of system calls, their arguments, return values,
and notes, refer to the [System Call Table](System-Calls-Table).
This overview explains the purpose and organisation of the MVM system calls.

