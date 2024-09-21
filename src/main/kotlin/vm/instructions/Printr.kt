package vm.instructions

import data.registers.enumIdenifies.RegisterType
import registers
import vm.core.InstructionBuild


/**
 * Prints the decimal representation of the value stored in a specified register.
 *
 * Example Usage: `PRINTR G1` (Prints the value in general-purpose register G1 to the console)
 */
class Printr(private val register: RegisterType) : InstructionBuild {
    override val name: String = "PRINTR"


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
