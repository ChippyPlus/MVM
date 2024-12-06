# MVM Data Types

The Micro Virtual Machine (MVM) supports several fundamental data types. These types are used in instructions, system
calls, and within the standard library functions. The MVM uses a 64-bit architecture, but some instructions and
functions can operate on smaller data types as specified by the `SETTYPE` instruction. All values are stored in memory
as 64-bit Longs unless otherwise specified. The data type stored in the registers is tracked by using the `RegisterData`
class which you can change at any time using the `SETTYPE` instruction. If an instruction or system call that operates
on a smaller data type is used, but the register is a larger type, then the value will be truncated to fit that type.

## Fundamental Data Types

### Integer Types

| Type    | Size (bits) | Range                                                   | Notes                                                                |
|---------|-------------|---------------------------------------------------------|----------------------------------------------------------------------|
| `Byte`  | 8           | -128 to 127                                             | Signed 8-bit integer.                                                |
| `Short` | 16          | -32,768 to 32,767                                       | Signed 16-bit integer.                                               |
| `Int`   | 32          | -2,147,483,648 to 2,147,483,647                         | Signed 32-bit integer.                                               |
| `Long`  | 64          | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 | Signed 64-bit integer.  This is the default data type for registers. |

### Floating-Point Types

| Type     | Size (bits) | Notes                                              |
|----------|-------------|----------------------------------------------------|
| `Float`  | 32          | Single-precision floating-point number (IEEE 754). |
| `Double` | 64          | Double-precision floating-point number (IEEE 754). |

### String Type

Strings are represented as null-terminated sequences of characters in memory. Each character occupies one word (64
bits).

### Boolean Type

Boolean values are represented using 64-bit Longs. The integer value of 0 represents false, and 1 represents true.

## Register Data Types

The `SETTYPE` instruction allows you to change the data type stored in a register at runtime. This allows for flexible
use of memory by being able to change data types as needed.

| Data Type | Description                                               | Size (bits) | Example                     |
|-----------|-----------------------------------------------------------|-------------|-----------------------------|
| `Byte`    | 8-bit signed integer                                      | 8           | `settype G1 Byte`           |
| `Short`   | 16-bit signed integer                                     | 16          | `settype G1 Short`          |
| `Int`     | 32-bit signed integer                                     | 32          | `settype G1 Int`            |
| `Long`    | 64-bit signed integer (default for registers).            | 64          | `settype G1 Long` (default) |
| `Float`   | 32-bit single-precision floating-point number (IEEE 754). | 32          | `settype X1 Float`          |
| `Double`  | 64-bit double-precision floating-point number (IEEE 754). | 64          | `settype X1 Double`         |

## Memory Representation

All values are stored in memory as 64-bit Longs regardless of the data type. This is to ensure consistency throughout
the memory. You can use smaller data types in registers, but memory always works with 64-bit Longs.

This document describes the data types used within the MVM. It's essential to understand these types when writing
assembly code for the MVM. 


