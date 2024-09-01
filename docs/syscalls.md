| System Call Number (S1) | Argument 1 (S2)    | Argument 2 (S3)      | Argument 3 (S4) | Name of Call   | Description                                                                 |  Result (R2)                           |
|-------------------------|--------------------|----------------------|-----------------|----------------|-----------------------------------------------------------------------------|----------------------------------------|
| 1                       | `fd: Int`          | `buffer: Long`       | `count: Int`    | `read`         | Reads data from a file into a buffer.                                       |  `Number of bytes read`                |
| 2                       | `fd: Int`          | `buffer: Long`       | `count: Int`    | `write`        | Writes data from a buffer to a file.                                        |  `Number of bytes written`             |
| 3                       | `pathname: String` | `flags: Int`         | `mode: Int`     | `open`         | Opens a file with specified flags and creation mode.                        |  `File descriptor`                     |
| 4                       | `fd: Int`          |                      |                 | `close`        | Closes a file.                                                              |  `N/A`                                 |
| 5                       |                    |                      |                 | `fork`         | Creates a child process that is a copy of the current process.              |  `Process ID of the child process`     |
| 6                       | `status: Int`      |                      |                 | `exit`         | Terminates the current process with the specified exit status.              |  `N/A`                                 |
| 7                       | `pid: Int`         | `status: Long`       |                 | `wait`         | Waits for a child process to terminate and retrieves its exit status.       |  `Exit status of the child process`    |
| 8                       | `fd: Int`          | `buffer: Long`       | `count: Int`    | `read`         | Reads data from a socket into a buffer.                                     |  `Number of bytes read`                |
| 9                       | `fd: Int`          | `buffer: Long`       | `count: Int`    | `write`        | Writes data from a buffer to a socket.                                      |  `Number of bytes written`             |
| 10                      | `domain: Int`      | `type: Int`          | `protocol: Int` | `socket`       | Creates a new socket with the specified address family, type, and protocol. |  `Socket descriptor`                   |
| 11                      | `fd: Int`          | `address: ByteArray` |                 | `bind`         | Binds a socket to a specific network address.                               |  `N/A`                                 |
| 12                      | `fd: Int`          | `backlog: Int`       |                 | `listen`       | Puts a socket into listening mode.                                          |  `N/A`                                 |
| 13                      | `fd: Int`          | `address: ByteArray` |                 | `accept`       | Accepts a connection request on a listening socket.                         |  `Socket descriptor of the connection` |
| 14                      | `fd: Int`          | `address: ByteArray` |                 | `connect`      | Establishes a connection to a socket.                                       |  `N/A`                                 |
| 15                      |                    | `tv: Long`           |                 | `gettimeofday` | Gets the current system time.                                               |  `System time`                         |
| 16                      |                    | `tv: Long`           |                 | `settimeofday` | Sets the system time.                                                       |  `N/A`                                 |
| 17                      | `status: Int`      |                      |                 | `exit`         | Terminates the current process with the specified exit status.              |  `N/A`                                 |
| 18                      |                    |                      |                 | `fork`         | Creates a child process that is a copy of the current process.              |  `Process ID of the child process`     |
| 19                      | `pathname: String` | `argv: Any`          | `envp: Any`     | `execve`       | Replaces the current process with a new program.                            |  `N/A`                                 |
| 20                      |                    |                      |                 | `getpid`       | Retrieves the process ID of the current process.                            |  `Process ID`                          |
| 21                      |                    |                      |                 | `getppid`      | Retrieves the process ID of the parent process.                             |  `Parent Process ID`                   |
| 22                      |                    |                      |                 | `getuid`       | Retrieves the user ID of the current process.                               |  `User ID`                             |
| 23                      |                    |                      |                 | `getgid`       | Retrieves the group ID of the current process.                              |  `Group ID`                            |
| 24                      |                    |                      |                 | `getcwd`       | Retrieves the current working directory.                                    |  `Current working directory`           |
| 25                      | `path: String`     |                      |                 | `chdir`        | Changes the current working directory.                                      |  `N/A`                                 |
| 26                      | `path: String`     |                      |                 | `stat`         | Retrieves file status information.                                          |  `File status`                         |
| 27                      | `path: String`     |                      |                 | `mkdir`        | Creates a directory.                                                        |  `N/A`                                 |
| 28                      | `path: String`     |                      |                 | `rmdir`        | Removes a directory.                                                        |  `N/A`                                 |
| 29                      | `path: String`     |                      |                 | `creat`        | Creates a file.                                                             |  `File descriptor`                     |
| 30                      | `path: String`     |                      |                 | `unlink`       | Removes a file.                                                             |  `N/A`                                 |


**Extra Info!!!!!**

* **Result Storage:** The table now includes columns for `Success/Failure (R1)` and `Result (R2)`.
* **Success/Failure:** The success/failure status will be stored in `R1`, typically with `0` indicating success and `1` indicating failure.
* **Result:** The actual result of the syscall will be stored in `R2`. The meaning of the result varies depending on the syscall (e.g., file descriptor, number of bytes read, process ID).

**Example:**

* A `read` syscall might have `R1` set to `0` (success) and `R2` containing the number of bytes read from the file.
* A `mkdir` syscall might have `R1` set to `0` (success) and `R2` would not contain a meaningful value (it could be unused). 
