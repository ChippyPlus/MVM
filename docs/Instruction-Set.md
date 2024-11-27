# MVM Instruction Set

This document details the MVM (Micro Virtual Machine) instruction set. Instructions are categorised for clarity. The MVM
uses a 64-bit architecture, and all instructions are 64 bits wide unless otherwise specified. Multi-word instructions
use two words. The MVM uses a stack-based architecture for function calls and return values.

## Data Transfer Instructions

These instructions move data between registers and memory.

### LIT

**`lit` `Destination Register` `Value` (Long Literal)**

Loads a 64-bit integer literal `Value` into the `Destination` register

```assembly
lit `G1` `10`       // G1 = 10
```

### XLIT

**`xlit` `Destination Register` `Value` (Double/Float Literal)**

Loads a floating-point literal `Value` into the `Destination` register. The data type (Float or Double) is determined at
runtime based on the data type assigned to the register by the `SETTYPE` instruction.

```assembly
xlit `X1` `3.14159` // X1 = 3.14159 (as a Double)
```

To load as a single-precision Float:

```assembly
settype `X1` `Float` // Set X1's data type to Float
xlit `X1` `3.14159` // X1 = 3.14159 (as a Float)
```

### MOV

**`mov` `Source Register` `Destination Register` (Register Value)**

Moves a value from the `Source` register to the `Destination` register.

```assembly
mov `G1` `G2`       // G2 = G1
```

### SWP

**`swp` `Register1` `Register2` (Register Value)**

Swaps the values of `Register1` and `Register2`.

```assembly
swp `G1` `G2`       // G1 <-> G2 (G1 and G2 exchange values)
```

### SETTYPE

**`settype` `Target Register` `DataType` (RegisterDataType)**

Sets the data type of the `Target` register. If the current value is too large or too small for the new data type, it
will be truncated.

```assembly
settype `G1` `Long` // G1 is now a Long
```

The available data types are: `Byte`, `Short`, `Int`, `Long`, `Float`, `Double`.

### STORE

**`store` `Source Register` `MemoryAddress Register` (Register Value)**

Stores the value from the `Source` register into the memory location whose address is in the `MemoryAddress` register.

```assembly
store `G1` `G2`     // Memory[G2] = G1
```

### LOAD

**`load` `MemoryAddress Register` `Destination Register` (Register Value)**

Loads the value from the memory location specified by the `MemoryAddress` register into the `Destination` register.

```assembly
load `G2` `G1`      // G1 = Memory[G2]
```

### PUSH

**`push` `Source Register` (Register Value)**

Pushes the value from the `Source` register onto the stack. The stack grows downwards.

```assembly
push `G1`         // Push G1 onto the stack
```

### PUSHL

**`pushl` `Value` (Long Literal)**

Pushes the 64-bit integer literal `Value` onto the stack.

```assembly
pushl `100`       // Push 100 onto the stack
```

### POP

**`pop` `Destination Register` (Register Value)**

Pops a value from the top of the stack and stores it in the `Destination` register. Handles stack underflow.

```assembly
pop `G1`          // G1 = top of stack; pop the stack
```

### PEEK

**`peek` `Destination Register` (Register Value)**

Copies the value at the top of the stack into the `Destination` register without removing it from the stack. Handles
stack underflow.

```assembly
peek `G1`         // G1 = top of stack; stack unchanged
```

### DEALLOC

**`dealloc` `Address` (Memory Address)**

Deallocates the memory at the address held in the `Address` register.

```assembly
dealloc `G1`      // Deallocate memory at G1
```

## Arithmetic Instructions

These instructions perform arithmetic operations. Results are pushed onto the stack.

### ADD

**`add` `Addend1` `Addend2` `ResultDestination Register` (Register Value)**

Adds `Addend1` and `Addend2`; a result is pushed onto the stack.

```assembly
add `G1` `G2` `R4`    // Push G1 + G2 onto stack
```

### SUB

**`sub` `Minuend` `Subtrahend` `ResultDestination Register` (Register Value)**

Subtracts `Subtrahend` from `Minuend`; a result is pushed onto the stack.

```assembly
sub `G1` `G2` `R4`    // Push G1 - G2 onto stack
```

### MUL

**`mul` `Multiplier` `Multiplicand` `ResultDestination Register` (Register Value)**

Multiplies `Multiplier` and `Multiplicand`; a result is pushed onto the stack.

```assembly
mul `G1` `G2` `R4`    // Push G1 * G2 onto stack
```

### DIV

**`div` `Dividend` `Divisor` `ResultDestination Register` (Register Value)**

Divides `Dividend` by `Divisor` (integer division); a result is pushed onto the stack. Throws an exception if the
divisor is zero.

```assembly
div `G1` `G2` `R4`    // Push G1 / G2 onto stack
```

### MOD

**`mod` `Dividend` `Divisor` `ResultDestination Register` (Register Value)**

Calculates the modulo of `Dividend` and `Divisor`; a result is pushed onto the stack. Throws an exception if the divisor
is zero.

```assembly
mod `G1` `G2` `R4`    // Push G1 % G2 onto stack
```

### POW

**`pow` `Base` `Exponent` `ResultDestination Register` (Register Value)**

Raises `Base` to the power of `Exponent` (integer exponentiation); a result is pushed onto the stack. Handles potential
overflow.

```assembly
pow `G1` `G2` `R4`    // Push G1 ^ G2 onto stack
```

### XADD

**`xadd` `Addend1` `Addend2` `ResultDestination Register` (Register Value)**

Adds two floating-point values; a result is pushed onto the stack. Operands must be of the same type (Float or Double).

```assembly
xadd `X1` `X2` `R5`   // Push X1 + X2 onto stack
```

### XSUB

**`xsub` `Minuend` `Subtrahend` `ResultDestination Register` (Register Value)**

Subtracts two floating-point values; a result is pushed onto the stack. Operands must be of the same type.

```assembly
xsub `X1` `X2` `R5`   // Push X1 - X2 onto stack
```

### XMUL

**`xmul` `Multiplier` `Multiplicand` `ResultDestination Register` (Register Value)**

Multiplies two floating-point values; a result is pushed onto the stack. Operands must be of the same type.

```assembly
xmul `X1` `X2` `R5`   // Push X1 * X2 onto stack
```

### XDIV

**`xdiv` `Dividend` `Divisor` `ResultDestination Register` (Register Value)**

Divides two floating-point values; a result is pushed onto the stack. Operands must be of the same type. Throws an
exception if the divisor is zero.

```assembly
xdiv `X1` `X2` `R5`   // Push X1 / X2 onto stack
```

### XMOD

**`xmod` `Dividend` `Divisor` `ResultDestination Register` (Register Value)**

Calculates the modulo of two floating-point values; a result is pushed onto the stack. Operands must be of the same
type. Throws an exception if the divisor is zero.

```assembly
xmod `X1` `X2` `R5`   // Push X1 % X2 onto stack
```

### XPOW

**`xpow` `Base` `Exponent` `ResultDestination Register` (Register Value)**

Raises a floating-point value to a power; a result is pushed onto the stack. Operands must be of the same type.

```assembly
xpow `X1` `X2` `R5`   // Push X1 ^ X2 onto stack
```

## Bitwise Instructions

These instructions perform bitwise operations. Results are pushed onto the stack.

### AND

**`and` `Operand1` `Operand2` `ResultDestination Register` (Register Value)**

Performs a bitwise AND operation; a result is pushed onto the stack.

```assembly
and `G1` `G2` `R3`    // Push G1 & G2 onto stack
```

### OR

**`or` `Operand1` `Operand2` `ResultDestination Register` (Register Value)**

Performs a bitwise OR operation; a result is pushed onto the stack.

```assembly
or `G1` `G2` `R3`     // Push G1 | G2 onto stack
```

### XOR

**`xor` `Operand1` `Operand2` `ResultDestination Register` (Register Value)**

Performs a bitwise XOR operation; a result is pushed onto the stack.

```assembly
xor `G1` `G2` `R3`    // Push G1 ^ G2 onto stack
```

### NOT

**`not` `Operand` `ResultDestination Register` (Register Value)**

Performs a bitwise NOT operation; a result is pushed onto the stack.

```assembly
not `G1` `R3`      // Push ~G1 onto stack
```

### SHL

**`shl` `Value` `ShiftAmount` `ResultDestination Register` (Register Value)**

Shifts the bits in the `Value` register to the left by the amount specified in the `ShiftAmount` register; a result is
pushed onto the stack.

```assembly
shl `G1` `G2` `R3`    // Push G1 << G2 onto stack
```

### SHR

**`shr` `Value` `ShiftAmount` `ResultDestination Register` (Register Value)**

Shifts the bits in the `Value` register to the right by the amount specified in the `ShiftAmount` register; a result is
pushed onto the stack.

```assembly
shr `G1` `G2` `R3`    // Push G1 >> G2 onto stack
```

## Control Flow Instructions

These instructions control the flow of execution.

### JMP

**`jmp` `LineNumber` (LineNumber)**

Unconditionally jumps to the specified `LineNumber`.

```assembly
jmp `10`         // Jump to line 10
```

### JZ

**`jz` `LineNumber` (LineNumber)**

Jumps to the specified `LineNumber` if the Zero Flag (`I1`) register is zero.

```assembly
jz `20`          // Jump to line 20 if I1 == 0
```

### JNZ

**`jnz` `LineNumber` (LineNumber)**

Jumps to the specified `LineNumber` if the Zero Flag (`I1`) register is not zero.

```assembly
jnz `20`          // Jump to line 20 if I1 != 0
```

### CALL

**`call` `FunctionName` (FunctionName)**

Calls the function specified by `FunctionName`. Arguments are passed in F registers; the result (if any) is pushed onto
the stack.

```assembly
call `maths.pow`  // Call the pow function
```

### RET

**`ret`**

Returns from a function. The function's return value (if any) should have already been pushed onto the stack.

## Comparison Instructions

These instructions perform comparisons. Results (0 or 1) are pushed onto the stack.

### EQ

**`eq` `Operand1` `Operand2` (Register Value)**

Compares two values for equality. Pushes 1 if equal, 0 if not.

```assembly
eq `G1` `G2`        // Push 1 if G1 == G2, 0 otherwise
```

### GT

**`gt` `Operand1` `Operand2` (Register Value)**

Compares two values; pushes 0 if `Operand1` > `Operand2`, otherwise 1. Sets the Greater Than Flag (`I3`).

```assembly
gt `G1` `G2`        // Push 0 if G1 > G2, 1 otherwise. Set I3 (Greater Than Flag) accordingly.
```

### LT

**`lt` `Operand1` `Operand2` (Register Value)**

Compares two values; pushes 0 if `Operand1` < `Operand2`, otherwise 1. Sets the Greater Than Flag (`I3`).

```assembly
lt `G1` `G2`        // Push 0 if G1 < G2, 1 otherwise. Set I3 (Greater Than Flag) accordingly.
```

## String Instructions

These instructions operate on null-terminated strings in memory. Results are pushed onto the stack.

### STR

**`str` `DestinationAddressRegister` `StringLiteral` (String Literal)**

Stores the `StringLiteral` in memory. The address of the new string is stored in the `DestinationAddressRegister`.
Memory is allocated automatically.

```assembly
str `G1` `"Hello"`   // Store "Hello" at a new memory location; G1 holds the address
```

## I/O Instructions

These instructions handle input and output.

### PRINTS

**`prints`**

Prints the top value on the stack to the console.

```assembly
prints         // Print value at top of stack
```

### PRINTR

**`printr` `Register` (Register Value)**

Prints the value of the specified register to the console.

```assembly
printr `G1`       // Print value of G1
```

## System Calls

These instructions invoke operating system (kernel) functions.

### SYSCALL

**`syscall` `SystemCallID` `Argument1` `Argument2` `Argument3` (SystemCallID, Register Value)**

Executes a system call. Arguments are passed in `S` registers; the system call ID is in `S1`. The result (if any) is
pushed onto the stack. See the [System Call Table](System-Calls-Table) for details.

```assembly
lit `s1` `14`        // Get current time (system call ID 14)
syscall             // Make the system call; result is on stack
```

## Miscellaneous Instructions

### INR

**`inr` `Register` (Register Value)**

Checks if a register is null (uninitialized). Pushes 1 if null, 0 if not.

```assembly
inr `G1`          // Push 1 if G1 is null, 0 otherwise
```

### HELP

**`help` `Topic` (Topic)**

Displays help information for the specified `Topic` (instruction or standard library function).

```assembly
help `"add"`       // Display help for the ADD instruction
```

### DEALLOC

**`dealloc` `Address` (Memory Address)**

Deallocates memory at the specified `Address`.

```assembly
dealloc `G1`      // Deallocate memory pointed to by G1
```

### SLEEP

**`sleep` `Milliseconds` (Milliseconds)**

Pauses execution for the specified `Milliseconds`.

```assembly
sleep `G1`        // Pause for number of milliseconds in G1
```

## XFloats Instructions

These instructions perform floating-point arithmetic. Results are pushed onto the stack. Operands must be of the same
type (Float or Double).

### XADD

**`xadd` `Addend1` `Addend2` `ResultDestination Register` (Register Value)**

Adds two floating-point values; a result is pushed onto the stack.

```assembly
xadd `X1` `X2` `R5`   // Push X1 + X2 onto stack
```

### XSUB

**`xsub` `Minuend` `Subtrahend` `ResultDestination Register` (Register Value)**

Subtracts two floating-point values; a result is pushed onto the stack.

```assembly
xsub `X1` `X2` `R5`   // Push X1 - X2 onto stack
```

### XMUL

**`xmul` `Multiplier` `Multiplicand` `ResultDestination Register` (Register Value)**

Multiplies two floating-point values; a result is pushed onto the stack.

```assembly
xmul `X1` `X2` `R5`   // Push X1 * X2 onto stack
```

### XDIV

**`xdiv` `Dividend` `Divisor` `ResultDestination Register` (Register Value)**

Divides two floating-point values; a result is pushed onto the stack. Throws an exception if the divisor is zero.

```assembly
xdiv `X1` `X2` `R5`   // Push X1 / X2 onto stack
```

### XMOD

**`xmod` `Dividend` `Divisor` `ResultDestination Register` (Register Value)**

Calculates the modulo of two floating-point values; a result is pushed onto the stack. Throws an exception if the
divisor is zero.

```assembly
xmod `X1` `X2` `R5`   // Push X1 % X2 onto stack
```

### XPOW

**`xpow` `Base` `Exponent` `ResultDestination Register` (Register Value)**

Raises a floating-point value to a power; a result is pushed onto the stack.

```assembly
xpow `X1` `X2` `R5`   // Push X1 ^ X2 onto stack
```

### ITOF

**`itof` `Source Register` `Destination Register` (Register Value)**

Converts a `Long` from the `Source` register to a `Double` and stores it in the `Destination` register.

```assembly
itof `G1` `X1`      // X1 = G1 (as a Double)
```

### FTOI

**`ftoi` `Source Register` `Destination Register` (Register Value)**

Converts a `Double` from the `Source` register to a `Long` and stores it in the `Destination` register.

```assembly
ftoi `X1` `G1`      // G1 = X1 (truncated to a Long)
```

This detailed explanation, for example, provides a clear understanding of each instruction and its usage within the MVM.
Remember to consult the [System Call Table](System-Calls-Table) for details on system calls. // Comments are now //
instead of ;


