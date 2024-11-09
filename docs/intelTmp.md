| Register | Symbol | Name                     | Description                                                                                                                                              |
|----------|--------|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| I0       | ZF     | Zero Flag                | Set if the result of the last arithmetic or logical operation was zero.                                                                                  |
| I1       | SF     | Sign Flag                | Set if the result of the last arithmetic operation was negative.                                                                                         |
| I2       | GF     | Greater Flag             | Set if the result of the last comparison was "greater than" (op1 > op2).                                                                                 |
| I3       | EF     | Equal Flag               | Set if the last comparison resulted in equality (op1 == op2).                                                                                            |
| I4       | SCSF   | System Call Success Flag | Set if the last system call executed successfully. Provides a simple way to check for system call errors without relying on specific return codes in R2. |

true = 1
false = 0