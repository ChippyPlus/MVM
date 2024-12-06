# Informational Register Table

These registers hold status information and flags. They are not directly manipulated by most instructions, but
instructions may set their values based on the result of an operation.

| Register | Symbol | Name                     | Implemented | Description                                                                                                    | Notes                                                                                                                 |
|----------|--------|--------------------------|-------------|----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| I1       | ZF     | Zero Flag                | Yes         | Set to 1 if the result of the last arithmetic or logical operation was zero; otherwise, it's 0.                | Reset to 0 after each instruction that can change it (except jumps and branches).                                     |
| I2       | SF     | Sign Flag                | Yes         | Set to 1 if the result of the last arithmetic operation was negative; otherwise, it's 0.                       | Reset to 0 after each arithmetic operation.                                                                           |
| I3       | GF     | Greater Than Flag        | Yes         | Set to 0 if the result of the last comparison (`GT`, `LT`) was true; otherwise, it's 1.                        | Reset after each comparison.                                                                                          |
| I4       | EF     | Equal Flag               | Yes         | Set to 1 if the result of the last comparison (`EQ`) was true; otherwise, it's 0.                              | Reset after each comparison.                                                                                          |
| I5       | SCSF   | System Call Success Flag | No          | Set to 1 if the last system call executed successfully; otherwise, it's 0.                                     | Reset after each system call.                                                                                         |
| I6       | ENSF   | Error Non-Specific Flag  | Somewhat    | Set to 1 if a non-specific error (e.g., file not found, memory allocation failure) occurred, otherwise it's 0. | Set by several system calls, instructions, and functions that can result in errors. Resets after handling an error.   |
| I7       | ESF    | Error Specific Flag      | No          | Set to a specific error code in the case of an error; otherwise, it's 0.                                       | Set by functions, instructions and system calls. Contains the specific code for the error, which should be looked up. |
| I8       | PC     | Program Counter          | Yes         | Holds the address (line number) of the next instruction to be executed.                                        | Not directly modified by most instructions. Modified by `jmp`, `jz`, `jnz`, function calls and returns.               |
| I9       | SIGR   | Signal Received          | No          | Set to the signal received from another process; otherwise, it's 0.                                            | Reset to 0 after handling the signal.                                                                                 |
| I10      | MB     | Mailbox ID               | No          | Holds the ID of the mailbox for inter-process communication.                                                   | **IPC is still in development** \| Set when a new connection is made.                                                 |

## Notes

- **Resetting Flags:** Many flags are automatically reset after an operation that might modify them. This prevents the
  flags from interfering with later operations.
- **Error Handling:** `I6` (ENSF) and `I7` (ESF) are essential parts of your error handling mechanism. Use them to
  indicate that an error has occurred. You can check their values to perform fault handling in the program.


