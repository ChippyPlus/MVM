# MVM Standard Library Overview

The MVM standard library provides reusable functions to enhance MVM programs. These functions handle common tasks and offer higher-level abstractions.  All functions use the stack for arguments and return values.  The library is organised into modules, each focusing on a specific area of functionality.

## Library Modules

The standard library is structured into the following modules:

### String Functions

This module provides string manipulation functions. Functions operate on null-terminated strings, and memory management is handled automatically.  The available functions are:

- `strcmp`: Compares two strings lexicographically. Returns 1 if equal, 0 otherwise.
- `strcat`: Concatenates two strings.  Returns the address of the new string.
- `strcpy`: Copies a string from a source to a destination. Returns the destination address.
- `strlen`: Returns the length of a string.


### Array Functions

This module provides array-related functions. The functions support array creation, access, and manipulation. Arrays created with `createArray` can be cleared using `clean.array`.  The available functions are:

- `create`: Creates a new array. Returns the address of the new array.
- `get`: Gets an element from an array. Returns the element's value.
- `size`: Returns the size of an array.

### Math Functions

This module contains standard mathematical functions. For floating-point functions, operands must be of the same type.  The functions are:

- `min`: Returns the smaller of two values.
- `max`: Returns the larger of two values.
- `inc`: Increments a value by 1.
- `dec`: Decrements a value by 1.
- `neg`: Negates a value.
- `sq`: Squares a value.


### Clean Functions

This module handles clearing parts of memory automatically. The functions are

- `array`: Clears an array.
- `string`: Clears a string.

### IO Functions

This module handles simple I/O operations, including console input and output.  The functions are:

- `println`: Prints a string to the console with a newline.
- `readln`: Reads a line of input from standard input and returns its address.


### System Functions

This module is responsible for system-related functions The functions are

- `exit`: Exits the program


### Conversion Functions

This module contains data type conversion functions.  The available functions are:

- `asciiToInt`: Converts an ASCII character to its integer representation.
- `strtoint`: Converts a string to a, long.
- `findFirstString`: Finds the first occurrence of a character within a string.



## Calling Standard Library Functions

Standard library functions are called using the `CALL` instruction:

```assembly
call strings.strlen  // Call the strlen function
```

Arguments are passed in F registers (F1, F2, etc.), and the return value (if any) is pushed onto the stack.


## Further Information

Refer to the [Standard Library Table](Standard-Library-Table) for detailed information on each function, including argument types and return values.

