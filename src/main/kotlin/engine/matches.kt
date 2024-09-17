package engine

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.Execute
import errors
import helpers.toMemoryAddress
import helpers.toSuperRegisterType
import internals.instructions.Instruction

fun Execute.matches(line: MutableList<String>): Any {

	return when (val instruction = line[0].removePrefix("	")) {

		"LOOP" -> {
			// Loop G1
			val x = loopData.indented.first()

			x.forEach {
				println(it)
			}
		}

		"STRCPY" -> { // String Copy
			Instruction.StrCpy(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"STRCMP" -> {
			Instruction.StrCmp(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"STRCAT" -> {

			Instruction.StrCat(
				line[1].toSuperRegisterType(),
				line[2].toSuperRegisterType(),
			)

		}

		"SUBSTR" -> {

			Instruction.SubStr(
				line[1].toSuperRegisterType(),
				line[2].toSuperRegisterType(),
				line[3].toSuperRegisterType(),
			)

		}

		"FIND" -> { // Find Substring

			Instruction.Find(
				line[1].toSuperRegisterType(), line[2].toSuperRegisterType()
			)

		}

		"CPY" -> Instruction.Cpy(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

		"STRLEN" -> Instruction.Strlen(line[1].toSuperRegisterType())

		"PRINTR" -> Instruction.Printr(line[1].toSuperRegisterType())

		"STR" -> Instruction.Str(line[1].toSuperRegisterType(), line.joinToString(" ").split("\"")[1])

		"SYSCALL" -> {

			Instruction.Syscall(
				SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4
			)

		}

		"" -> {// ignore empty line
		}

		"//" -> {// ignore comments

		}


		"MOD" -> {
			/** MOD G1 G2*/
			Instruction.Mod(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"EQ" -> {
			/** EQ G1 G2*/
			Instruction.Eq(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"SHL" -> {
			Instruction.Shl(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"SHR" -> {
			Instruction.Shr(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"PEEK" -> {
			Instruction.Peek(line[1].toSuperRegisterType())
		}

		"POP" -> {
			Instruction.Pop(line[1].toSuperRegisterType())
		}

		"PUSH" -> {
			Instruction.Push(line[1].toSuperRegisterType())
		}

		"PRINTS" -> {
			Instruction.Prints()
		}

		"DIV" -> {
			Instruction.Div(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}


		"AND" -> {
			Instruction.And(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"OR" -> {
			Instruction.Or(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"XOR" -> {
			Instruction.Xor(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"NOT" -> {
			Instruction.Not(line[1].toSuperRegisterType())
		}


		"STORE" -> {
			Instruction.Store(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"LOAD" -> {
			Instruction.Load(line[1].toMemoryAddress(), line[2].toSuperRegisterType())
		}


		"LIT" -> {
			Instruction.Lit(line[1].toSuperRegisterType(), line[2].toLong())
		}

		"JMP" -> {
			Instruction.Jmp(line[1].toInt())
		}

		"JZ" -> {
			Instruction.Jz(line[1].toInt(), line[2].toSuperRegisterType())
		}

		"JNZ" -> {
			Instruction.Jnz(line[1].toInt(), line[2].toSuperRegisterType())
		}

		"MOV" -> {
			Instruction.Mov(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"ADD" -> {
			Instruction.Add(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"SUB" -> {
			Instruction.Sub(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		"MUL" -> {
			Instruction.Mul(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
		}

		else -> {
			errors.InvalidInstructionException(instruction)
		}


	}
}