package engine.v2

import data.registers.RegisterType
import data.registers.RegisterType.*

class TransMapIDs {

	val uRegisters = mutableMapOf<Char, RegisterType>()

	init {
		for (i in uRegisters) {
			uRegisters[i.key] = i.value
		}
	}


	val registers = mapOf(
		G1 to '!',
		G2 to '@',
		G3 to '#',
		G4 to '$',
		S0 to '%',
		S1 to '^',
		S2 to '&',
		S3 to '*',
		R1 to '(',
		R2 to ')',
		R3 to '-',
		R4 to '+',
		F1 to '¡',
		F2 to '™',
		F3 to '£',
		F4 to '¢',
		IF1 to '∞',
		IF2 to '§',
		IF3 to '¶',
		IF4 to '•'
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
		"printr" to 'C',
		"gt" to 'D',
		"lt" to 'E'
	)
}