| System Call Number (S1) | Argument 1 (S2)          | Argument 2 (S3)      | Argument 3 (S4) | Name of Call   | Description                                                                                           | Result (R2)                           |
|-------------------------|--------------------------|----------------------|-----------------|----------------|-------------------------------------------------------------------------------------------------------|---------------------------------------|
| 1                       | `fd: Int`                | `buffer: Long`       | `count: Int`    | `readFile`     | Reads data from a file into a buffer.                                                                 | `Number of bytes read`                |
| 2                       | `fd: Int`                | `buffer: Long`       | `count: Int`    | `writeFile`    | Writes data from a buffer to a file.                                                                  | `Number of bytes written`             |
| 3                       | `path: String`           | `flags: Int`         | `mode: Int`     | `open`         | Opens a file with specified flags and creation mode.                                                  | `File descriptor`                     |
| 4                       | `fd: Int`                |                      |                 | `close`        | Closes a file.                                                                                        |                                       |
| 5                       |                          |                      |                 | `fork`         | Creates a child process that is a copy of the current process.                                        | `Process ID of the child process`     |
| 6                       | `status: Int`            |                      |                 | `exit`         | Terminates the current process with the specified exit status.                                        |                                       |
| 7                       | `pid: Int`               | `status: Long`       |                 | `wait`         | Waits for a child process to terminate and retrieves its exit status.                                 | `Exit status of the child process`    |
| 8                       | `fd: Int`                | `buffer: Long`       | `count: Int`    | `readSock`     | Reads data from a socket into a buffer.                                                               | `Number of bytes read`                |
| 9                       | `fd: Int`                | `buffer: Long`       | `count: Int`    | `writeSock`    | Writes data from a buffer to a socket.                                                                | `Number of bytes written`             |
| 10                      | `domain: Int`            | `type: Int`          | `protocol: Int` | `socket`       | Creates a new socket with the specified address family, type, and protocol.                           | `Socket descriptor`                   |
| 11                      | `fd: Int`                | `address: ByteArray` |                 | `bind`         | Binds a socket to a specific network address.                                                         |                                       |
| 12                      | `fd: Int`                | `backlog: Int`       |                 | `listen`       | Puts a socket into listening mode.                                                                    |                                       |
| 13                      | `fd: Int`                | `address: ByteArray` |                 | `accept`       | Accepts a connection request on a listening socket.                                                   | `Socket descriptor of the connection` |
| 14                      | `fd: Int`                | `address: ByteArray` |                 | `connect`      | Establishes a connection to a socket.                                                                 |                                       |
| 15                      | `tv: Long`               |                      |                 | `getTimeOfDay` | Gets the current system time.                                                                         | `System time`                         |
| 16                      | `path: String`           | `argv: Long`         | `envp: Long`    | `execve`       | Replaces the current process with a new program.                                                      |                                       |
| 17                      |                          |                      |                 | `getpid`       | Retrieves the process ID of the current process.                                                      | `Process ID`                          |
| 18                      |                          |                      |                 | `getuid`       | Retrieves the user ID of the current process.                                                         | `User ID`                             |
| 19                      |                          |                      |                 | `getcwd`       | Retrieves the current working directory.                                                              | `Current working directory`           |
| 20                      | `path: String`           |                      |                 | `chdir`        | Changes the current working directory.                                                                |                                       |
| 21                      | `path: String`           |                      |                 | `mkdir`        | Creates a directory.                                                                                  |                                       |
| 22                      | `path: String`           |                      |                 | `rmdir`        | Removes a directory.                                                                                  |                                       |
| 23                      | `path: String`           |                      |                 | `create`       | Creates a file.                                                                                       | `File descriptor`                     |
| 24                      | `path: String`           |                      |                 | `remove`       | Removes a file.                                                                                       |                                       |
| 25                      | `address: MemoryAddress` | `len: Int`           |                 | `writeIo`      | Writes a string to the screen starting at `address` in memory and incrementing and printing the ASCII |

**Extra Info!!!!!**

* **Result Storage:** The table now includes columns for `Success/Failure (R1)` and `Result (R2)`.
* **Success/Failure:** The success/failure status will be stored in `R1`, typically with `0` indicating success and `1`
  indicating failure.
* **Result:** The actual result of the syscall will be stored in `R2`. The meaning of the result varies depending on the
  syscall (e.g., file descriptor, number of bytes read, process ID).

**Example:**

* A `read` syscall might have `R1` set to `0` (success) and `R2` containing the number of bytes read from the file.
* A `mkdir` syscall might have `R1` set to `0` (success) and `R2` would not contain a meaningful value (it could be
  unused). 
