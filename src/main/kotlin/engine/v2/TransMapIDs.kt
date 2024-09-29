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
        R3 to '-'
    )
    val instructions = mapOf(
        "mov" to 'a',
        "add" to 'b',
        "sub" to 'c',
        "mul" to 'd',
        "div" to 'e',
        "mod" to 'f',
        "jmp" to 'g',
        "jz" to 'h',
        "jnz" to 'i',
        "push" to 'j',
        "pop" to 'k',
        "peek" to 'l',
        "syscall" to 'm',
        "load" to 'n',
        "store" to 'o',
        "and" to 'p',
        "or" to 'q',
        "xor" to 'r',
        "not" to 's',
        "shl" to 't',
        "shr" to 'u',
        "lit" to 'v',
        "eq" to 'w',
        "strlen" to 'x',
        "str" to 'y',
        "prints" to 'z',
        "printr" to 'A'
    )
}