package internals.instructions

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType

@Deprecated("Ready to phase this out. It just adds un-necessary complexity")
@Suppress("unused")
class Instruction {

    data class StrCpy(val source: SuperRegisterType, val destination: SuperRegisterType)

    data class StrCmp(val string1: SuperRegisterType, val string2: SuperRegisterType)

    data class StrCat(
		val string1: SuperRegisterType,
		val string2: SuperRegisterType,
    )

    data class SubStr(
		val string: SuperRegisterType,
		val start: SuperRegisterType,
		val length: SuperRegisterType,
    )

    data class Find(val string: SuperRegisterType, val substring: SuperRegisterType)

    data class Cpy(val register1: SuperRegisterType, val register2: SuperRegisterType)

    data class Strlen(val addressRegister: SuperRegisterType)

    // TODO it um. its here ioAbstractions.printr
    data class Printr(val register: SuperRegisterType)

    // TODO it um. its here instructions.strings.Str
    data class Str(val targetAddress: SuperRegisterType, val string: String)

    // TODO make a description!!!! Its a modulus operator
    data class Mod(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /** Represents an EQ instruction, which checks if the value in [operand1] == [operand2]
     * If they are equal, it sets `R2` to 0, otherwise, it sets the `R2` to 1
     *
     * @param [operand1] The first operand register.
     * @param [operand2] The second operand register.
     */
    data class Eq(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    @Deprecated("Eh it never worked and useless")
    data class Halt(val nothing: Nothing? = null)

    /**
     * Represents a MOV instruction, which moves a value from a source register to a destination register.
     *
     * @param source The source register.
     * @param destination The destination register.
     */
    data class Mov(val source: SuperRegisterType, val destination: SuperRegisterType)

    /**
     * Represents an ADD instruction, which adds the values in two registers and stores the result in a destination register.
     *
     * @param operand1 The first operand register.
     * @param operand2 The second operand register.
     */
    data class Add(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents a SUB instruction, which subtracts the value in one register from the value in another register and stores the result in a destination register.
     *
     * @param operand1 The first operand register.
     * @param operand2 The second operand register.
     */
    data class Sub(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents a MUL instruction, which multiplies the values in two registers and stores the result in a destination register.
     *
     * @param operand1 The first operand register.
     * @param operand2 The second operand register.
     */
    data class Mul(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents a DIV instruction, which divides the value in one register by the value in another register and stores the result in a destination register.
     *
     * @param operand1 The dividend register.
     * @param operand2 The divisor register.
     */
    data class Div(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents a JMP instruction, which unconditionally jumps to a specified instruction address.
     *
     * @param targetAddress The address of the instruction to jump to.
     */
    data class Jmp(val targetAddress: Int)

    /**
     * Represents a JZ instruction, which jumps to a specified instruction address if the value in a test register is zero.
     *
     * @param targetAddress The address of the instruction to jump to.
     * @param testRegister The register to test for zero.
     */
    data class Jz(val targetAddress: Int, val testRegister: SuperRegisterType)

    /**
     * Represents a JNZ instruction, which jumps to a specified instruction address if the value in a test register is not zero.
     *
     * @param targetAddress The address of the instruction to jump to.
     * @param testRegister The register to test for non-zero.
     */
    data class Jnz(val targetAddress: Int, val testRegister: SuperRegisterType)


    /**
     * Represents a PEEK instruction, which pushes the value from a source register onto the stack.
     *
     * @param destination The destination register.
     */
    data class Peek(val destination: SuperRegisterType)

    /**
     * Represents a PUSH instruction, which pushes a value from a source register onto the stack.
     *
     * @param source The source register.
     */
    data class Push(val source: SuperRegisterType)

    /**
     * Represents a POP instruction, which pops a value from the stack into a destination register.
     *
     * @param destination The destination register.
     */
    data class Pop(val destination: SuperRegisterType)

    /**
     * Represents a SYSCALL instruction, which executes a system call.
     *
     * WARNING`argument1` to `argument3` should always be a System Register!!!!!
     * @param systemCallNumber The system call number.
     * @param argument1 The first argument register.
     * @param argument2 The second argument register.
     * @param argument3 The third argument register.
     */
    data class Syscall(
		val systemCallNumber: SuperRegisterType,
		val argument1: SuperRegisterType,
		val argument2: SuperRegisterType,
		val argument3: SuperRegisterType
    )

    /**
     * Represents a LOAD instruction, which loads a value from a memory address into a destination register.
     *
     * @param memoryAddress The memory address to load from.
     * @param destination The destination register.
     */
    data class Load(val memoryAddress: MemoryAddress, val destination: SuperRegisterType)

    /**
     * Represents a STORE instruction, which stores a value from a source register into a memory address.
     *
     * @param source The source register.
     * @param memoryAddress The memory address to store to.
     */
    data class Store(val source: SuperRegisterType, val memoryAddress: SuperRegisterType)

    /**
     * Represents an AND instruction, which performs a bitwise AND operation between two registers and stores the result in a destination register.
     *
     * @param operand1 The first operand register.
     * @param operand2 The second operand register.
     */
    data class And(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents an OR instruction, which performs a bitwise OR operation between two registers and stores the result in a destination register.
     *
     * @param operand1 The first operand register.
     * @param operand2 The second operand register.
     */
    data class Or(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents an XOR instruction, which performs a bitwise XOR operation between two registers and stores the result in a destination register.
     *
     * @param operand1 The first operand register.
     * @param operand2 The second operand register.
     */
    data class Xor(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

    /**
     * Represents a NOT instruction, which performs a bitwise NOT operation on a register and stores the result in the same register.
     *
     * @param operand The operand register.
     */
    data class Not(val operand: SuperRegisterType)

    /**
     * Represents a SHL instruction, which shifts the bits of a register to the left by a specified amount.
     *
     * @param operand The operand register.
     * @param shiftAmount The amount to shift the bits.
     */
    data class Shl(val operand: SuperRegisterType, val shiftAmount: SuperRegisterType)

    /**
     * Represents an SHR instruction, which shifts the bits of a register to the right by a specified amount.
     *
     * @param operand The operand register.
     * @param shiftAmount The amount to shift the bits.
     */
    data class Shr(val operand: SuperRegisterType, val shiftAmount: SuperRegisterType)

    /**
     * Represents a LIT instruction, which loads a literal value into a specified register.
     *
     * @param destination The destination register.
     * @param value The literal value to load.
     */
    data class Lit(val destination: SuperRegisterType, val value: Long)

    /**
     * Represents The PRINT_STACK(PRINTS) instruction, which prints the contents of the stack.
     *
     * @param nothing This parameter is required for data classes but has no effect.
     */
    data class Prints(val nothing: Nothing? = null)


}