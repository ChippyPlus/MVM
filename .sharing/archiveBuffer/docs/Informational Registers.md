# Informational Register Table

--------

| #  | Register name | Symbol | Symbol name              | Description                                                                     | Notes                                                                                     |
|----|---------------|--------|--------------------------|---------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| 1  | I1            | ZF     | Zero Flag                | When ever an instruction returns 0. This flag gets enabled. Else ZF is set to 0 | -                                                                                         |
| 2  | I2            | SF     | Sign Flag                | If an instructions result is negative SF is set to 1. Else 0                    | -                                                                                         |
| 3  | I3            | GF     | Greater Than Flag        | When the instruction `GT` returns 1, GF is set to one. Else 0                   | `LT` is the same as `GT` but inverted                                                     |
| 4  | I4            | EF     | Equal Flag               | If `EQ` returns 1. EF is set to 1, else 0                                       | -                                                                                         |
| 5  | I5            | SCSF   | System Call Success Flag | Is set to 1 if an error has occurred, else 0                                    | Not  implemented                                                                          |
| 6  | I6            | ENSF   | Error Non-Specific Flag  | Is set to 1 if an error has occurred, else 0                                    | Not fully implemented                                                                     |
| 7  | I7            | ESF    | Error Specific Flag      | Is set to a specific code based on an error of a instruction or call            | Not  implemented                                                                          |
| 8  | I8            | PC     | Program counter          | Is the current value of the PC                                                  | The Jump instructions really just change I8, you can change it with `LIT` to jump as well |
| 9  | I9            | SIGR   | Signal receiver          | Is set to what ever signal was sent to the VM                                   | -                                                                                         |
| 10 | I10           | -      | -                        | -                                                                               | -                                                                                         |