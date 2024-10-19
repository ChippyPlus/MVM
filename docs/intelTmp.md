| Register | Symbol | Name                     | Description                                                                                                                                                         |
|----------|--------|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| I0       | ZF     | Zero Flag                | Set if the result of the last arithmetic or logical operation was zero.                                                                                             |
| I1       | SF     | Sign Flag                | Set if the result of the last arithmetic operation was negative.                                                                                                    |
| I2       | GF     | Greater Flag             | Set if the result of the last comparison was "greater than" (op1 > op2).                                                                                            |
| I3       | EF     | Equal Flag               | Set if the last comparison resulted in equality (op1 == op2).                                                                                                       |
| I4       | NF     | Null Flag                | Set if the last register accessed or the memory location accessed was null (not initialized). This flag helps to detect potential errors due to uninitialized data. | 
| I5       | STF    | String Flag              | Set if the last string operation (STRCMP, STRCAT) was successful (e.g., strings were equal in STRCMP, concatenation in STRCAT did not result in an error).          |
| I6       | MAF    | Memory Allocation Flag   | Set if the last attempt to allocate memory (for a string or array) was successful.  Could be used to manage memory more explicitly in assembly code.                | 
| I7       | SCSF   | System Call Success Flag | Set if the last system call executed successfully. Provides a simple way to check for system call errors without relying on specific return codes in R2.            |

true = 1
false = 0