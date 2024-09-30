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
        R4 to "+"
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