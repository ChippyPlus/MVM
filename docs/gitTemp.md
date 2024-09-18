## System Calls in MVM

This document provides a detailed description of the system calls supported by the Micro Virtual Machine (MVM). System
calls provide a mechanism for programs running within the VM to request services from the underlying host operating
system or the MVM environment.

### Overview

System calls extend the functionality of MVM programs, enabling them to perform operations that require interaction with
the host system, such as:

* **File I/O:**  Reading from and writing to files.
* **Process Management:**  Obtaining process information (not yet fully implemented).
* **Time:** Retrieving the current system time.
* **Console Output:**  Displaying text on the console.

### System Call Mechanism

* **`SYSCALL` Instruction:**  The `SYSCALL` instruction is used to initiate a system call.
* **System Call Number (`S1`):**  The `S1` register must contain the system call number, identifying the desired system
  call.
* **Arguments (`S2`, `S3`, `S4`):**  Arguments to the system call (if required) are placed in system registers `S2`,
  `S3`, and `S4`.
* **Result (`R2`):** The result of the system call, if any, is typically returned in the `R2` register.

### System Call Table

| System Call Number (S1) | Argument 1 (S2)         | Argument 2 (S3)         | Argument 3 (S4)  | Name of Call   | Description                                                                 | Result (R2)                      | Is Implemented | Notes                                                                                                                 |
|-------------------------|-------------------------|-------------------------|------------------|----------------|-----------------------------------------------------------------------------|----------------------------------|----------------|-----------------------------------------------------------------------------------------------------------------------|
| 1                       | File Descriptor         | Buffer Address          | -                | `readFile`     | Reads data from a file into a buffer in memory.                             | Starting address of bytes read   | Yes            | The file descriptor should refer to a file opened in read mode.                                                       |
| 2                       | File Descriptor         | Buffer Address          | -                | `writeFile`    | Writes data from a buffer in memory to a file.                              | -                                | Yes            | The file descriptor should refer to a file opened in write mode.                                                      |
| 3                       | Path String             | -                       | -                | `openFile`     | Opens a file specified by the path.                                         | File descriptor                  | Yes            | The path string should be a null-terminated string in memory.                                                         |
| 4                       | File Descriptor         | -                       | -                | `closeFile`    | Closes the file associated with the file descriptor.                        | -                                | Yes            | -                                                                                                                     |
| 5                       | Exit Status             | -                       | -                | `exit`         | Terminates the current process with the specified exit status.              | -                                | Yes            | -                                                                                                                     |
| 6                       | Process ID              | Status                  | -                | `wait`         | Waits for a child process to terminate and retrieves its exit status.       | Exit status of the child process | No             | This system call is not yet implemented.                                                                              |
| 7                       | File Descriptor         | Buffer Address          | -                | `readSock`     | Reads data from a socket into a buffer.                                     | Number of bytes read             | No             | This system call is not yet implemented.                                                                              | 
| 8                       | File Descriptor         | Buffer Address          | -                | `writeSock`    | Writes data from a buffer to a socket.                                      | Number of bytes written          | No             | This system call is not yet implemented.                                                                              | 
| 9                       | Domain                  | Type                    | Protocol         | `socket`       | Creates a new socket with the specified address family, type, and protocol. | Socket descriptor                | No             | This system call is not yet implemented.                                                                              |
| 10                      | File Descriptor         | Address                 | -                | `bind`         | Binds a socket to a specific network address.                               | -                                | No             | This system call is not yet implemented.                                                                              |
| 11                      | File Descriptor         | Backlog                 | -                | `listen`       | Puts a socket into listening mode.                                          | -                                | No             | This system call is not yet implemented.                                                                              |
| 12                      | File Descriptor         | Address                 | -                | `accept`       | Accepts a connection request on a listening socket.                         | Socket descriptor of connection  | No             | This system call is not yet implemented.                                                                              |
| 13                      | File Descriptor         | Address                 | -                | `connect`      | Establishes a connection to a socket.                                       | -                                | No             | This system call is not yet implemented.                                                                              |
| 14                      | -                       | -                       | -                | `getTimeOfDay` | Gets the current system time in milliseconds.                               | System time                      | Yes            | -                                                                                                                     |
| 15                      | Path String             | Arguments Array Address | Environment Vars | `execve`       | Replaces the current process with a new program.                            | -                                | No             | This system call is not yet implemented.                                                                              |
| 16                      | -                       | -                       | -                | `getpid`       | Retrieves the process ID of the current process.                            | Process ID                       | Yes            | -                                                                                                                     | 
| 17                      | -                       | -                       | -                | `getuid`       | Retrieves the user ID of the current process.                               | User ID                          | Yes            | -                                                                                                                     |
| 18                      | -                       | -                       | -                | `getcwd`       | Retrieves the current working directory.                                    | Current working directory        | No             | This system call is not yet implemented.                                                                              |
| 19                      | Path String             | -                       | -                | `chdir`        | Changes the current working directory.                                      | -                                | No             | This system call is not yet implemented.                                                                              |
| 20                      | Path String             | -                       | -                | `mkdir`        | Creates a directory.                                                        | -                                | No             | This system call is not yet implemented.                                                                              |
| 21                      | Path String             | -                       | -                | `rmdir`        | Removes a directory.                                                        | -                                | No             | This system call is not yet implemented.                                                                              |
| 22                      | Path String             | -                       | -                | `create`       | Creates a file.                                                             | File descriptor                  | No             | This system call is not yet implemented. You might consider using a combination of `openFile` with appropriate flags. | 
| 23                      | Path String             | -                       | -                | `remove`       | Removes a file.                                                             | -                                | No             | This system call is not yet implemented.                                                                              |
| 24                      | Address of String Start | -                       | -                | `writeIo`      | Writes a null-terminated string to the console.                             | -                                | Yes            | The address should point to the first character of the null-terminated string in memory.                              | 

### Error Handling

System calls might encounter errors during execution. Common error scenarios include:

* **File Not Found:** The specified file does not exist.
* **Permission Denied:**  The VM does not have the necessary permissions to access the file or resource.
* **Invalid Arguments:**  Incorrect arguments were passed to the system call.
* **Network Errors:**  Problems with network connectivity prevent the socket operation from completing.

When a system call encounters an error, the VM will typically:

1. **Terminate Execution:** Stop the program immediately.
2. **Print an Error Message:**  Display a message indicating the nature of the error.
3. **Exit with an Error Code:** Terminate the VM with a specific error code associated with the error type.

### Example: Reading from a File

```assembly
// Open a file for reading
LIT S1 3               // System call 3 (openFile) 
STR G1 "input.txt"    // Load the file path into memory, put address in G1
MOV S2 G1              // Move the file path address into S2 (argument 1)
SYSCALL                // Execute the system call; the file descriptor is returned in R2

// Read data from the file
MOV G2 R2              // Move the file descriptor from R2 into G2
LIT S1 1               // System call 1 (readFile)
MOV S2 G2              // File descriptor (from G2) to S2 
LIT S3 100             // Buffer address (example: address 100)
LIT S4 50              // Number of bytes to read (example: 50)
SYSCALL

// ... (process the data read from the file) 

// Close the file
LIT S1 4               // System call 4 (closeFile)
MOV S2 G2              // File descriptor to S2
SYSCALL
```

### Additional Notes
* **Security:** System calls are a potential security concern, as they provide a bridge between the VM's isolated
  environment and the host system. Implement system calls with careful consideration for security implications,
  especially those related to file access, process management, and network operations.
* **Future Expansion:** The current set of system calls in MVM is limited. As the VM evolves, additional system calls
  will be added to provide more functionality and greater control over the host environment.
