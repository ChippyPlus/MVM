package engine.v2

class ExecutionV2 {
    fun execute(f: String) {
        val nf = f.split((0).toChar())
        val transMapIDs = TransMapIDs()
        val instructionIds = TransMapIDs().instructions
        val registerIds = TransMapIDs().registers
        for (instruct in nf) {
            println(instruct)
        }
    }
}