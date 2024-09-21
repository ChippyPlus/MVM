package vm.core

import data.registers.Register
import data.registers.enumIdenifies.RegisterType
import registers

data class InstructionInfo(val instruction: InstructionBuild, val arguments: List<String>) {
    fun getName(): String {
        return arguments[0]
    }
}

/**
 * Represents a buildable instruction for the virtual machine.
 */
interface InstructionBuild {
    val name: String
    fun execute()
    fun debug(): String
    fun collectOutput(): String?
}

/**
 * Loads a literal value into a register.
 *
 * Example Usage: `LIT G1, 10.0` (Loads the double value 10.0 into general-purpose register G1)
 */
class Lit(private val register: RegisterType, private val value: Double) : InstructionBuild {
    override val name: String = "LIT"

    /**
     * Executes the LIT instruction.
     *
     * @param arg1 The destination register ([RegisterType]) where the literal value will be stored.
     * @param arg2 The literal value (a [Double]) to load into the register.
     * @throws IllegalArgumentException If the provided arguments are not of the expected types
     *                                  or if the register type is invalid.
     */
    override fun execute() {
        registers.write(register, Register(value))
    }

    override fun debug(): String {
        return "LIT: Loading $value into register $register"
    }

    override fun collectOutput(): String? {
        return null
    }
}

/**
 * Prints the decimal representation of the value stored in a specified register.
 *
 * Example Usage: `PRINTR G1` (Prints the value in general-purpose register G1 to the console)
 */
class Printr(private val register: RegisterType) : InstructionBuild {
    override val name: String = "PRINTR"

    /**
     * Executes the PRINTR instruction.
     *
     * @param arg1 The register ([RegisterType]) whose value should be printed.
     * @param arg2 (Unused)
     * @throws IllegalArgumentException If the provided argument is not a [RegisterType] or the register is invalid.
     */
    override fun execute() {
        val registerValue = registers.read(register).value!!
        when (register.getType()) {
            Byte -> println(registerValue.toInt().toByte())
            Short -> println(registerValue.toInt().toShort())
            Int -> println(registerValue.toInt())
            Long -> println(registerValue.toLong())
            Float -> println(registerValue.toFloat())
            Double -> println(registerValue)
        }
    }

    override fun debug(): String {
        return "PRINTR: Printing the value in register $register"
    }

    override fun collectOutput(): String? {
        val registerValue = registers.read(register)
        return registerValue.value?.toString() // Collect the output for potential use
    }
}

/**
 * Moves the value from one register to another.
 *
 * Example Usage: `MOV G1, R1` (Copies the value from return register R1 to general-purpose register G1)
 */
class Mov(private val source: RegisterType, private val destination: RegisterType) : InstructionBuild {
    override val name: String = "MOV"

    /**
     * Executes the MOV instruction.
     *
     * @param arg1 The source register ([RegisterType]) from which to copy the value.
     * @param arg2 The destination register ([RegisterType]) where the value will be stored.
     * @throws IllegalArgumentException If the provided arguments are not [RegisterType]s or if the registers are invalid.
     */
    override fun execute() {
        val sourceValue = registers.read(source).value ?: error("Source register $source is not initialized")
        registers.write(destination, Register(sourceValue))
    }

    override fun debug(): String {
        return "MOV: Moving value from register $source to register $destination"
    }

    override fun collectOutput(): String? {
        return null
    }
}


class SetType(private val register: RegisterType, private val type: Any) : InstructionBuild {
    override val name = "SETTYPE"
    override fun execute() {
        when (type) {
            Byte.Companion -> register.updateType(type)
            Short.Companion -> register.updateType(type)
            Int.Companion -> register.updateType(type)
            Long.Companion -> register.updateType(type)
            Float.Companion -> register.updateType(type)
            Double.Companion -> register.updateType(type)
        }
    }

    override fun debug(): String {
        return "Changing register $registers to type $type"
    }

    override fun collectOutput(): String? {
        return null
    }

}