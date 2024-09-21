package vm.core


/**
 * Represents a buildable instruction for the virtual machine.
 */
interface InstructionBuild {
    val name: String
    fun execute()
    fun debug(): String
    fun collectOutput(): String?
}

