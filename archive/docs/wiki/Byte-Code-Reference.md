## Bytecode Reference

This page provides a detailed description of the bytecode format used by MVM (Micro Virtual Machine). The bytecode is
the low-level representation of your MVM programs, which is directly executed by the VM. It's important to note that in
MVM, the bytecode uses human-readable instruction mnemonics (like "MOV" and "PUSH") rather than numerical opcodes.

### Overview

* **Purpose:** The bytecode serves as a compact and efficient representation of instructions and data for the VM.
* **Structure:** The bytecode is organized into a sequence of instructions. Each instruction has a specific format
  consisting of:
	* **Instructions:** A textual representation of the instruction (e.g., `MOV`, `ADD`, `JMP`, etc.).
	* **Arguments:** Values or data associated with the instruction (
	  e.g., `registers` `IDs`, `memory addresses`,   `literal values` and `strings`).

### Instruction Format

MVM uses a variable-length instruction format. Instructions can have different sizes depending on the number and type of
arguments.

### **General Format:**

Instructions are represented as space-separated strings, with the first string being the instruction mnemonic and the
following Register, string or Int literal, representing the instruction.

### **Example:**

```
MOV G1 R4   // Move the value in register G1 to register R4
ADD G1 G2   // Add the values in G1 and G2, store the result in R4
            // (All of the instructions store the result in R4. We will speak about this later)
```

### **Argument Encoding:**

| Operand Type  | Description                                                                    |
|---------------|--------------------------------------------------------------------------------|
| Register      | Represented by their textual names (`G1` to `G4`, `S1` to `S4`, `R1` to `R4`). |
| Literal Value | Represented as decimal numbers (These are **64** bit integers ).               |
| Strings       | Represented as an array of ASCII(127) characters in double quotes              |

### **Specific Instructions:**

For each instruction in your VM's instruction set, provide the following details:
Here's a table with all the instructions you need in the VM → [Instruction Table](Instruction-Table)

### **Error Handling:**

The VM is designed to handle errors gracefully during runtime. If an error occurs, the VM will:

1. **Print Error Message**: Display a descriptive error message indicating the cause of the error.
2. **Terminate Execution**: Stop the program immediately.
3. **Exit with Error Code**: Exit with a specific error code corresponding to the type of error encountered.  
   For a comprehensive list of error codes and their meanings, please refer to the → [Error-table](Error-table).  

