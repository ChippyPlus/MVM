package vm.instructions

import data.registers.enumIdenifies.RegisterType
import registers
import vm.core.InstructionBuild


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