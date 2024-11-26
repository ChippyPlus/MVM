# Registers

MVM (Micro Virtual Machine) uses registers to store data that is actively being processed.
Registers are like small,
fast storage locations directly within the VM. MVM has several different types of registers, each with a specific
purpose:

## Register Types

* **General Purpose Registers (G0-G9):**
	* Used for arithmetic operations, data storage, and general calculations.
	* Can be used as operands for many instructions.
	* Example:  `LIT G1 10` (Loads value 10 into general-purpose register G1)

* **System Registers (S0-S3):**
	* Used specifically for system calls.
	* `S0` holds the system call ID.
	* `S1`, `S2`, and `S3` are used to pass arguments to the system call.
	* Example: `LIT S0 3` (Loads system call ID 3—`openFile`—into S0)

* **Return Registers (R0-R9):**
	* Primarily used to store the results of arithmetic, comparison, bitwise, and string operations.
	* `R4` is the conventional register for return values from functions and many standard library calls.
	* `R3` typically holds the result of bitwise operations.
	* `R2` is often used for the results of system calls.
	* Example: `ADD G1 G2` (The result of the addition is stored in R4)

* **Function Argument Registers (F0-F9):**
	* Used exclusively for passing arguments to functions (subroutines).
	* When you call a function using the `CALL` instruction, arguments are placed in these registers before the call.
	* Example: (Loads value 10 into F1 to be passed as an argument to a function and squares it)
  ```
  LIT F1 10 // Loading 10 as argument 1
  CALL MATHS.SQ   // Using the SQUARE function to square 10.
  ```  

* **Internal Function Registers (IF0-IF9):**
	* Reserved for internal use by standard library functions.
	* User programs should generally not directly access or modify these registers.
	  They are used for temporary storage
	  and intermediate calculations within the standard library.

## Register Size

All registers in MVM are 64 bits wide, meaning they can hold 64-bit integer values.

## Register Access

Registers are accessed using their names (e.g., `G1`, `S0`, `R4`, `F1`, `IF1`) in MVM assembly instructions.

## Register Initialization

The initial values of registers when a program starts are always set to null. Keep in mind you can’t read null values

## Example: Function Call with Arguments

```assembly
LIT F1 5      // Load argument 1 into F1
LIT F2 10     // Load argument 2 into F2
CALL test   // Call the function "my_function" with the arguments in F1 and F2
POP R1        // Retrieve the return value (if any) from the stack into R1
```

In this example, the `CALL` instruction passes the values in `F1` and `F2` as arguments to `my_function`.
The return
value from the function (if there is one) will be placed on the stack, and the example uses `POP R1` to retrieve it.

## Memory vs. Registers

Registers are much faster to access than memory.
Store frequently used data in registers for better performance.
Use memory for larger data structures or data that doesn't need to be accessed constantly.
The `STORE` and `LOAD` instructions transfer data between registers and memory.

Refer to the [Instruction Set](Instruction-Set) documentation for more examples
of register usage in different instructions.

