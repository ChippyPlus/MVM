package vm.instructions

import data.registers.Register
import data.registers.enumIdenifies.RegisterType
import registers
import vm.core.InstructionBuild

/**
 * Moves the value from one register to another.
 *
 * Example Usage: `MOV G1, R1` (Copies the value from return register R1 to general-purpose register G1)
 */
class Mov(private val source: RegisterType, private val destination: RegisterType) : InstructionBuild {
    override val name: String = "MOV"


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
