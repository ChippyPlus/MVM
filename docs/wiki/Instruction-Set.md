# Instruction Set

This page provides detailed descriptions of each instruction in the MVM (Micro Virtual Machine) assembly language. The
instructions are grouped by category to make it easier to find the information you need.

## Data Transfer Instructions

These instructions move data between registers, memory, and the stack.

* **`LIT register value`:** Loads a 64-bit integer literal (`value`) into the specified `register`.
	* Example: `LIT G1 10` (Loads the value 10 into register G1)

* **`MOV register1 register2`:** Moves the value from `register1` to `register2`.
	* Example: `MOV G1 G2` (Copies the value from G1 to G2)

* **`STORE register1 register2`:** Stores the value from `register1` into the memory address held in `register2`.
	* Example: `STORE G1 G2` (Stores the value of G1 at the memory address contained in G2)

* **`LOAD register1 register2`:** Loads the value from the memory address held in `register1` into `register2`.
	* Example: `LOAD G1 G2` (Loads the value at the memory address in G1 into G2)

* **`PUSH register/value`:** Pushes a value (either a register or a literal value) onto the stack.
	* Examples: `PUSH G1`, `PUSH 10`

* **`POP register`:** Pops a value from the top of the stack and stores it in the specified `register`.
	* Example: `POP G1`

* **`PEEK register`:** Copies the value at the top of the stack into `register` *without* removing it from the stack.
	* Example: `PEEK G1`

* **`CPY register1 register2`:**  Copies the value of `register1` into `register2`. This is essentially a synonym for
  `MOV`.  (Consider if you really need both `CPY` and `MOV` or if one is sufficient.)

* **`DEALLOC register`:** This instruction deallocates the memory address held in the `register` by setting the memory
  at that location to null. Used for freeing memory no longer needed (like strings).

## Arithmetic Instructions

These instructions perform arithmetic operations. The results are usually stored in `R4`.

* **`ADD op1 op2`:** Adds the values of `op1` and `op2` (which can be registers or literal values).
* **`SUB op1 op2`:** Subtracts `op2` from `op1`.
* **`MUL op1 op2`:** Multiplies `op1` by `op2`.
* **`DIV op1 op2`:** Divides `op1` by `op2` (integer division).
* **`MOD op1 op2`:** Calculates the remainder when `op1` is divided by `op2`.
* **`POW register1 register2`:** Raises `register1` to the power of `register2` (integer exponentiation).

## Bitwise Instructions

These instructions perform bitwise operations. Results are stored in `R3`.

* **`AND op1 op2`:**  Bitwise AND of `op1` and `op2`.
* **`OR op1 op2`:**  Bitwise OR.
* **`XOR op1 op2`:**  Bitwise XOR.
* **`NOT register`:** Bitwise NOT of the value in `register`.
* **`SHL register1 register2/value`:** Shifts the bits in `register1` left by the amount specified in `register2` or a
  literal value.
* **`SHR register1 register2/value`:** Shifts the bits in `register1` right by the specified amount.

## Control Flow Instructions

These instructions control the order in which instructions are executed.

* **`JMP address`:**  Unconditionally jumps to the instruction at `address` (a line number).
* **`JZ address register`:** Jumps to `address` *if* the value in `register` is zero.
* **`JNZ address register`:** Jumps to `address` if `register` is *not* zero.
* **`CALL function_name `:** Calls a subroutine (function). The arguments are passed in F registers, and the return
  value (if any) is pushed onto the stack. See [Subroutines](Subroutines) for details.
* **`RET`:**  Returns from a subroutine. See [Subroutines](Subroutines).

## Comparison Instructions

These instructions compare two values and store the result in `R4` (0 if true, 1 if false).

* **`EQ op1 op2`:** Checks if `op1` is equal to `op2`.
* **`GT op1 op2`:** Checks if `op1` is greater than `op2`.
* **`LT op1 op2`:** Checks if `op1` is less than `op2`.

## String Instructions

These instructions work with strings (which are represented as null-terminated character arrays in memory).

* **`STR register string_literal`:** Stores the `string_literal` (enclosed in double quotes) in memory and places its
  starting address in the specified `register`.
* **`STRLEN register`:**  Calculates the length of the string (excluding the null terminator) at the address stored in
  `register` and stores the length in `R4`.
* **`STRCMP register1 register2`:**  Compares the two strings at the addresses in `register1` and `register2`. Sets `R4`
  to 0 if the strings are equal, 1 if not equal.
* **`STRCAT register1 register2`:** Concatenates the string at `register2` to the end of the string pointed to by
  `register1`. The starting address of the new concatenated string is placed in `R4`.
* **`STRCPY register1 register2`:** Copies the string from the address in `register1` to the address in `register2`.

## Input/Output Instructions

* **`PRINTS`:** Prints the decimal value at the top of the stack to the console.
* **`PRINTR register`:** Prints the decimal value in `register` to the console.
* **`SYSCALL`:** Executes a system call. See the [System Call Table](System-Calls-Table) for details on system call IDs,
  arguments, and return values.

## Miscellaneous Instructions

* **`INR register`:** Checks if `register` is null. Sets `IF4` to 0 if the register is null, 1 otherwise.
* **`HELP string`:** Displays help information for the specified instruction or standard library function.