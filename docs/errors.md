| Error Number | Error Type                            | Description                                                                                                               |
|--------------|---------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| 1            | `InvalidRegisterException`            | Thrown when attempting to access or manipulate an invalid register.                                                       |
| 2            | `InvalidMemoryAddressException`       | Thrown when attempting to access a memory address that is outside the bounds of the allocated memory.                     |
| 3            | `InvalidInstructionException`         | Thrown when encountering an invalid Instruction (an instruction that is not recognized).                                  |
| 4            | `StackOverflowException`              | Thrown when the stack overflows (the stack grows beyond its maximum capacity).                                            |
| 5            | `EmptyStackException`                 | Thrown when attempting to pop or peek from an empty stack.                                                                |
| 6            | `ArithmeticException`                 | Thrown during arithmetic operations, such as division by zero or overflow.                                                |
| 7            | `SystemCallException`                 | Thrown when a system call fails or encounters an error.                                                                   |
| 8            | `FileAccessException`                 | Thrown when an error occurs while accessing a file (e.g., file not found, permission denied).                             |
| 9            | `SocketException`                     | Thrown when an error occurs during a socket operation (e.g., connection refused, address in use).                         |
| 10           | `MemoryAllocationException`           | Thrown when the KVM fails to allocate memory.                                                                             |
| 11           | `InvalidInstructionArgumentException` | Thrown when an instruction is provided with invalid arguments (e.g., an invalid register name or an incorrect data type). |
| 12           | `NullRegisterException`               | Thrown when an register with a null value being accessed or manipulated                                                   |

**Additional Considerations:**

* **Logging:** May happen soon, like some debug mode?
* **Error Recovery:** To maybe add traces soon.
* **Termination:** All errors will terminate the main program.
