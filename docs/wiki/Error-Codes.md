# Error Codes

| Exit Code | Exception Name                        | Description                                                                         |
|-----------|---------------------------------------|-------------------------------------------------------------------------------------|
| 1         | `InvalidRegisterException`            | Thrown when an invalid register is accessed or manipulated.                         |
| 2         | `InvalidMemoryAddressException`       | Thrown when attempting to access an invalid memory address (out of bounds or null). |
| 3         | `InvalidInstructionException`         | Thrown when an invalid or unrecognized instruction is encountered.                  |
| 4         | `InvalidSystemCallException`          | Thrown when an invalid system call ID is used.                                      |
| 5         | `StackOverflowException`              | Thrown when the stack overflows (exceeds its maximum capacity).                     |
| 6         | `EmptyStackException`                 | Thrown when attempting to pop or peek from an empty stack.                          |
| 7         | `ArithmeticException`                 | Thrown during arithmetic errors (e.g., division by zero, overflow).                 |
| 8         | `SystemCallGeneralException`          | Thrown when a system call encounters a general error.                               |
| 9         | `FileAccessException`                 | Thrown during file access errors (e.g., file not found, permission denied).         |
| 10        | `SocketException`                     | Thrown during socket operation errors.                                              |  
| 11        | `MemoryAllocationException`           | Thrown when memory allocation fails.                                                |
| 12        | `InvalidInstructionArgumentException` | Thrown when an instruction has invalid arguments (wrong type, incorrect number).    |
| 13        | `NullRegisterException`               | Thrown when accessing or manipulating a register with a null value.                 |
| 14        | `NullAddressException`                | Thrown when accessing a null memory address.                                        |
| 15        | `InvalidFileDescriptorException`      | Thrown when using an invalid file descriptor.                                       |
| 16        | `NotFreeMemoryException`              | Thrown when attempting to write to memory that is not free.                         |
| 17        | `GeneralBitwiseException`             | Thrown during bitwise operation errors.                                             |
| 18        | `GeneralControlFlowException`         | Thrown during control flow errors (jumps, branches).                                |
| 19        | `GeneralDataTransferException`        | Thrown during data transfer errors.                                                 |
| 20        | `GeneralIoAbstractionsException`      | Thrown during I/O errors.                                                           |
| 21        | `GeneralMemoryException`              | Thrown during memory operation errors (load, store).                                |
| 22        | `GeneralStackOperationsException`     | Thrown during stack operation errors.                                               |
| 23        | `GeneralStringException`              | Thrown during string operation errors.                                              |
| 24        | `InvalidArgumentException`            | Thrown when a required argument is missing.                                         |
| 25        | `InvalidArgumentFormatException`      | Thrown when an argument is of the incorrect format or type.                         |
| 26        | `MissingLibraryException`             | Thrown when a library or standard library function cannot be found.                 |