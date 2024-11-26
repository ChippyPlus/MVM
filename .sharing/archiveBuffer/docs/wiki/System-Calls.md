# System Calls

System calls in MVM (Micro Virtual Machine) allow your assembly programs to request services from the underlying
operating system or environment. These services include file operations, gathering system information, and more.

## Making System Calls

The `SYSCALL` instruction executes a system call. Before executing `SYSCALL`, you must set the following registers:

* **S0:** The system call ID (a number that identifies the specific system call).
* **S1 - S3:** Arguments for the system call (if any). The meaning of these arguments depends on the specific system
  call being invoked.

System calls typically return a result in register **R2**.
Some system calls may not return a value.
All errors
encountered during system calls will immediately terminate the program.*

```assembly
LIT S0 14      // System call ID for "time" (get current time)
SYSCALL        // Execute the system call. The current time is now in R2.
MOV R2 G1    // Store the returned time from R2 into general register G1.
```

## System Call Table

The [System Call Table](System-Calls-Table) provides a comprehensive list of all available system calls, their IDs,
arguments, and return values.

## Common System Calls

This section describes some of the frequently used system calls:

* **File I/O:**
	* **`openFile(path)` (ID: 3):** Opens the file specified by the null-terminated string at the memory address in
	  `S1`. Returns a file descriptor in `R2`.
	* **`readFile(fd)` (ID: 1):** Reads data from the file identified by the file descriptor (`fd`) in `S1`. Stores the
	  starting address of the read data in memory in `R2`.
	* **`writeFile(fd, buffer)` (ID: 2):** Writes data to a file.
	  holds the file descriptor, and `S2` contains the
	  starting address of the buffer in memory that holds the data to be written.
	* **`closeFile(fd)` (ID: 4):** Closes the file specified by the file descriptor in `S1`.

* **Process Management:**
	* **`exit(exitCode)` (ID: 5):** Terminates the MVM program with the exit code provided in `S1`.

* **System Information:**
	* **`time()` (ID: 14):** Returns the current time in milliseconds in `R2`.
	* **`getpid()` (ID: 16):** Returns the process ID of the MVM instance in `R2`.
	* **`getuid()` (ID: 17):** Returns the user ID in `R2`.

* **Input/Output:**
	* **`writeIo(stringAddr)` (ID: 24):** Prints the null-terminated string at the memory address in `S1` to the
	  console.
	* **`readIo()` (ID: 25):** Reads a line from standard input and stores it as a null-terminated string in memory.
	  Returns the starting address of the string in `R2`.

* **Array Operations:**
	* **`createArray(size)` (ID: 26):** Creates an array of the specified `size` (in `S1`).
	  Returns the base address of
	  the allocated array in `R2`.
	* **`arraySet(arrayAddr, index, value)` (ID: 27):** Sets the element at `index` of the array at `arrayAddr` to
	  `value`. Arguments: `arrayAddr` in `S1`, `index` in `S2`, `value` in `S3`.
	* **`arrayGet(arrayAddr, index)` (ID: 28):** Returns the value of the element at `index` in the array at address
	  `arrayAddr`.
	  Arguments are passed in the same order as `arraySet`.
	  The element's value is returned in `R2`.

## Example: Writing to a File

```assembly
// ...get file descriptor (fd) in R1 using openFile...

// String to write (must be null-terminated)
STR S2 "Hello, file!\n"  // S2 holds the string's address

LIT S0 2    // System call ID for writeFile
MOV R2 S1   // File descriptor (fd) as the first argument in S1
CPY S2 S2    // Address of the string to write as the second argument in S2
SYSCALL      // Call writeFile

```
