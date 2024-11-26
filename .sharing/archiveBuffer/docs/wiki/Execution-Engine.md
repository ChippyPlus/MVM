## Execution Engine in MVM

This document provides a deeper look at the heart of the Micro Virtual Machine (MVM)—its Execution Engine. This
component is responsible for actually running the instructions (bytecode) of guest programs.

### Overview

The Execution Engine is the core of the MVM. It orchestrates the fetch-decode-execute cycle that drives program
execution. It interacts with other VM components, including:

* **Instruction Decoder:** To retrieve and interpret instructions.
* **Registers:** To read and write data from registers.
* **Memory:** To access data and instructions stored in memory.
* **Stack:** To manage the stack for temporary data and function calls (if supported).
* **System Call Handler:** To execute system calls requested by guest programs.

### Fetch-Decode-Execute Cycle

The Execution Engine operates in a continuous loop, following the fetch-decode-execute cycle:

1. **Fetch:**

* Retrieves the next instruction from memory using the Program Counter (PC). The PC holds the address of the instruction
  to be executed.

2. **Decode:**

* Passes the fetched instruction to the Instruction Decoder.
* The Instruction Decoder analyses the instruction and identifies:
	* The instruction mnemonic (e.g. "MOV", "ADD", "JMP").
	* The operands associated with the instruction (e.g. register IDs, memory addresses, literal values).

3. **Execute:**

* Based on the decoded instruction and its operands, the Execution Engine performs the necessary actions:
	* **Data Transfer:** Moving data between registers or between memory and registers.
	* **Arithmetic Operations:** Performing arithmetic calculations.
	* **Stack Operations:** Pushing and popping values on the stack.
	* **Control Flow:** Changing the execution flow using jump instructions.
	* **Memory Access:** Reading or writing data to memory.
	* **System Calls:** Invoking the system call handler to interact with the underlying operating system.

4. **Update PC:**

* The Program Counter (PC) is incremented to point to the next instruction to be executed.
* If a control flow instruction (like `JMP`) was executed, the PC is updated to the target address instead.

### Implementation

The Execution Engine is implemented in four parts in this order.

* **Create Function Table:** Creating a table that maps instruction mnemonics to corresponding execution functions and
  its arguments.
* **Switch Statement:** Using a `switch` (`when` in Kotlin) statement to branch based on the instructions.
* **Interpretation:** Directly interpreting the instructions.
* **Execution:** Executes the interpreted code by calling the appropriate functions

### Error Handling

The Execution Engine plays a crucial role in error handling. It should:

* **Check Operand Validity:** Verify that operands are valid (e.g. valid register names, memory addresses within
  bounds).
* **Handle Arithmetic Errors:** Detect and handle potential arithmetic errors (e.g. division by zero).
* **Handle System Call Errors:** Capture and handle errors that might be returned by system calls.
* **Throw Exceptions:** If an error is detected, the Execution Engine should throw an appropriate exception (
  e.g. `InvalidRegisterException`, `InvalidMemoryAddressException`, `ArithmeticException`, `etc`).

### Example (Simplified):

```
// (Illustrative code - not actual Kotlin code)

while (VM is running) {
  instruction = fetchInstruction(PC); 
  decodedInstruction = decodeInstruction(instruction);

  switch (decodedInstruction.mnemonic) {
    case "MOV":
      executeMov(decodedInstruction.operands);
      break;
    case "ADD":
      executeAdd(decodedInstruction.operands);
      break;
    // ... (cases for other instructions) ...
    case "SYSCALL":
      executeSyscall(decodedInstruction.operands);
      break;
    default:
      // Handle invalid instruction
  } 

  updateProgramCounter(PC); 
}
```

### Key Points:

* **Central Role:** The Execution Engine is the core component that drives the VM's operations.
* **Fetch-Decode-Execute Cycle:** The VM executes programs by repeatedly fetching, decoding, and executing instructions.
* **Error Handling:** The Execution Engine must handle errors effectively to ensure the VM's stability and reliability.



