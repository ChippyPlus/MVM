# MVM Registers

The MVM (Micro Virtual Machine) uses registers to store data actively being processed.
Registers provide fast access to data during program execution. The MVM uses several types of registers, each serving a
specific purpose. All registers are 64 bits wide unless otherwise noted.

## Register Types

### General Purpose Registers (G1-G10)

These registers are used for general computations, data storage, and calculations. They can be used as operands in all
instructions.

```assembly
lit G1 10       // Load the literal value 10 into register G1
```

### System Registers (S1-S4)

These registers are used for system calls. `S1` holds the system call ID; `S2`, `S3`, and `S4` pass arguments to the
system call.

```assembly
lit S1 24            // Load system call 24 (writeIo) into S1
mov G1 S2            // Pass string address to S2
syscall              // Make system call
```

### Return Registers (R1-R10)

These registers typically hold the results of operations (arithmetic, comparison, bitwise) and system calls

- `R1` is usually returned by standard library operations
- `R2` is conventionally used for system call return values.
- `R3` typically holds results from bitwise operations.
- `R4` conventionally holds results from arithmetic, comparison, and string operations.
- `R5` conventionally holds results from floating-point operations. Note `R5` By default is a FLOAT Register type so
  this
  is recommended.
- `R6` conventionally holds results from `INR` instructions.

```assembly
add G1 G2 R4   // Result of addition is in R4
```

### Function Argument Registers (F1-F10)

These registers are used exclusively for passing arguments to functions (subroutines). Arguments are placed in these
registers **before** the function call.

```assembly
lit F1 10           // Load argument 1 into F1
call maths.sq       // Call the square function
prints              // Print the result on the stack, 100. 10 * 10 
```

### Floating-Point Registers (X1-X10)

These registers are used for floating-point arithmetic operations. By default these are Floats. Not Doubles

```assembly
xlit X1 3.14159 //  Load a floating-point literal
```

### Intel Registers (I1-I10)

These registers hold status flags and other metadata. They are set implicitly by various instructions and are not
directly modified by most instructions.

- `I1`  Set if the last operation resulted in zero.
- `I2`  Set if the last arithmetic operation's result was negative.
- `I3`  Set according to the last `GT` or `LT` comparison.
- `I4`  Set if the last `EQ` comparison was true.
- `I5`  Indicates system call success.
- `I6`  Indicates a general error.
- `I7`  Holds a code specifying the error type.
- `I8`  Holds the address of the next instruction to execute.
- `I9`  Set when a signal is received.
- `I10` Stores the ID for inter-process communication.

see for more information → [Informational Registers](Informational-Registers.md)

## Register Size

All registers are 64 bits wide, capable of storing 64-bit integer values, or a floating point number of any type,
depending on the register data type assigned using the `SETTYPE` instruction.
The options are BYTE, SHORT, INT, LONG, FLOAT, and DOUBLE.

## Register Access

Registers are accessed by name (e.g., `G1`, `S2`, `R4`, `X1`, `I8`) in MVM assembly instructions.

## Register Initialization

Registers are initialised to 0 when a process is created.

## Example: Function Call

```assembly
lit F1 5         // Load argument 1 into F1
lit F2 1         // Load argument 2 into F2
call test        // Call the test function
pop R1           // Get the return value from the stack
```

## Memory vs. Registers

Registers offer faster access than memory. Store frequently accessed data in registers. Use memory for larger data
structures or data not requiring constant access. `STORE` and `LOAD` instructions transfer data between registers and
memory.

Refer to the [Instruction Set](Instruction-Set.md) documentation for more examples of register usage.


