package engine.v2

import data.registers.enumIdenifiers.SuperRegisterType.*

class TransMapIDs {
    val registers = mapOf(
        G1 to '!',
        G2 to '@',
        G3 to '#',
        G4 to '$',
        S1 to '%',
        S2 to '^',
        S3 to '&',
        S4 to '*',
        R1 to '(',
        R2 to ')',
        R3 to '-',
        R4 to '+'
    )

    val uRegisters = mapOf(
        '!' to G1,
        '@' to G2,
        '#' to G3,
        '$' to G4,
        '%' to S1,
        '^' to S2,
        '&' to S3,
        '*' to S4,
        '(' to R1,
        ')' to R2,
        '-' to R3,
        '+' to R4
    )
    val uInstruction = mapOf(
        'a' to "mov",
        'b' to "lit",
        'c' to "add",
        'd' to "sub",
        'e' to "mul",
        'f' to "div",
        'g' to "mod",
        'h' to "jmp",
        'i' to "jz",
        'j' to "jnz",
        'k' to "push",
        'l' to "pop",
        'm' to "peek",
        'n' to "store",
        'o' to "load",
        'p' to "shl",
        'q' to "shr",
        'r' to "and",
        's' to "or",
        't' to "xor",
        'u' to "not",
        'v' to "prints",
        'w' to "syscall",
        'x' to "str",
        'y' to "strlen",
        'z' to "strcmp",
        'A' to "strcat",
        'B' to "strcpy",
        'C' to "printr"
    )

    val instructions = mapOf(
        "mov" to 'a',
        "lit" to 'b',
        "add" to 'c',
        "sub" to 'd',
        "mul" to 'e',
        "div" to 'f',
        "mod" to 'g',
        "jmp" to 'h',
        "jz" to 'i',
        "jnz" to 'j',
        "push" to 'k',
        "pop" to 'l',
        "peek" to 'm',
        "store" to 'n',
        "load" to 'o',
        "shl" to 'p',
        "shr" to 'q',
        "and" to 'r',
        "or" to 's',
        "xor" to 't',
        "not" to 'u',
        "prints" to 'v',
        "syscall" to 'w',
        "str" to 'x',
        "strlen" to 'y',
        "strcmp" to 'z',
        "strcat" to 'A',
        "strcpy" to 'B',
        "printr" to 'C'


    )
}