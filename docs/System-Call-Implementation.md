# MVM System Call Implementation

This document details how system calls are implemented in the MVM (Micro Virtual Machine). System calls provide a
mechanism for user-space programs to request services from the kernel.

## System Call Interface

User programs invoke system calls using the `syscall` instruction. The system call ID is passed in register `S1`.
Arguments are passed in registers `S2`, `S3`, and `S4`. The result (if any) is stored in `R2` by the system call
handler.

## System Call Handling

The system call mechanism involves:

1. **`syscall` Instruction:** A user program executes the `syscall` instruction.

2. **Trap:** The VM's execution engine detects the `syscall` instruction. This triggers a trap or interrupt, switching
   execution to kernel mode.

3. **System Call Handler (`SystemCallHandler.kt`):** The system call handler:
	- Retrieves the system call ID from register `S1`.
	- Retrieves any arguments passed by the user program from registers `S2`, `S3`, and `S4`.
	- Calls the appropriate kernel function to handle the system call. This is handled in the `SystemCall.execute`
	  function which uses a `when` statement to call the correct function.
	- Stores the return values (if any) in `R2`.

4. **Return to User Mode:** Once the system call is handled, execution returns to the user program.

## Example: `readFile` System Call

Let's trace the steps for `readFile`:

1. **User Program:** A user program calls `readFile` using the `syscall` instruction:
   ```assembly
   lit S1 2         		// System call ID for readFile	
   str S2 "file name.txt"	// Address of the filename string
   syscall             		// Make system call
   printr R2           		// Retrieve the result (address of data) 
   ```

2. **Trap:** The `syscall` instruction causes a trap to the kernel.

3. **System Call Handler:**
	- The system call handler in `SystemCallHandler.kt` detects the system call ID (`2`).
	- It calls the `readFile` function in `internals.systemCalls.calls.readFile.kt`, passing the filename address (
	  `filename_address`) from register `S2`.

4. **`readFile` Function (`internals.systemCalls.calls.readFile.kt`):**

  ```kotlin
  fun SystemCall.readFile(nameX: RegisterType) = call("readFile") {
      val name = helpers.readRegisterString(nameX)
      val out = (vm.vfs.read(name) ?: errors.FileNotFoundException(name)) as String
      val spot = helpers.writeClosestString(out)
      registers.write(RegisterType.R2, spot)
  }
  ```

- This function interacts with the virtual file system (`vm.vfs`) to read the file's content.
- The function then uses the `writeClosestString` helper function to copy the data into memory.
- The function places the address of the data (from the VFS) on the stack.

5. **Return to User Mode:** The system call handler returns control to the user program. The user program can then
   access the data from the address stored in `R2`.

## Key Considerations

* **Error Handling:** Each system call function should implement robust error handling, returning error codes or raising
  exceptions. The system call handler should manage these errors appropriately.
* **Concurrency:** You'll need to ensure that system calls are handled concurrently if you have multiple processes
  running. Synchronisation mechanisms might be needed if a system call involves shared resources.
* **Kernel Design:** The design of your kernel (in `os/OS.kt`) determines how efficient and robust system calls are.
* **Documentation:** Clearly document each system call, its arguments, return values, and potential error conditions in
  your System Call Table and the associated documentation.

This detailed explanation clarifies the system call implementation in the MVM.
The system call handler is a central point where user programs interact with the kernel.
It's a key element in the design of your virtual machine.
Each system call is responsible for managing any necessary resources,
interacting with virtual devices, and performing necessary actions.
Return values are always stored on the stack, which is managed by the VM's runtime environment.


