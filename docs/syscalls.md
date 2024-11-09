# System Call Table

| ID (S0) | Name          | Description                                     | Arg 1 (S1)     | Arg 2 (S2)     | Arg 3 (S3) | Returns (Stack)                | Notes                            |
|---------|---------------|-------------------------------------------------|----------------|----------------|------------|--------------------------------|----------------------------------|
| 1       | `newFile`     | Creates a new file to VFS                       | File name      | -              | -          | -                              |                                  |
| 2       | `readFile`    | Reads data from the VFS into memory.            | File name      | -              | -          | Starting Address of bytes read |                                  |
| 3       | `writeFile`   | Writes data from memory to a file.              | File name      | Buffer content | -          | -                              |                                  |
| 4       | `listFiles`   | Lists all files in VFS                          | -              | -              | -          | -                              |                                  |
| 5       | `deleteFile`  | Deletes a file in                               | File name      | -              | -          | -                              |                                  |
| 6       | `exit`        | Terminates the VM.                              | Exit Code      | -              | -          | -                              |                                  |
| 14      | `time`        | Gets the current time (milliseconds).           | -              | -              | -          | Current Time (milliseconds)    |                                  |
| 16      | `getpid`      | Gets the process ID.                            | -              | -              | -          | Process ID                     |                                  |
| 17      | `getuid`      | Gets the user ID.                               | -              | -              | -          | User ID                        |                                  |
| 24      | `writeIo`     | Writes a null-terminated string to the console. | String Address | -              | -          | -                              |                                  |
| 25      | `readIo`      | Reads a line from standard input.               | -              | -              | -          | String Address                 | Allocates memory for the string. |
| 26      | `createArray` | Creates a new array.                            | Array Size     | -              | -          | Array Base Address             |                                  |
| 27      | `arraySet`    | Sets an array element.                          | Array Address  | Index          | Value      | -                              | Performs bounds checking.        |
| 28      | `arrayGet`    | Gets an array element.                          | Array Address  | Index          | -          | Value                          | Performs bounds checking.        |