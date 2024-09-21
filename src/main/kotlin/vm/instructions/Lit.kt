package vm.instructions

import data.registers.Register
import data.registers.enumIdenifies.RegisterType
import registers
import vm.core.InstructionBuild


/**
 * Loads a literal value into a register.
 *
 * Example Usage: `LIT G1, 10.0` (Loads the double value 10.0 into general-purpose register G1)
 */
class Lit(private val register: RegisterType, private val value: Double) : InstructionBuild {
    override val name: String = "LIT"


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
