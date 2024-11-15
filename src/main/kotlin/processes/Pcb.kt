package processes

import config
import vm.RegisterType


data class Pcb(
	val pid: Long,                       // Process ID (unique)
	val programPath: String,            // Path to the program file
	var state: ProcessState = ProcessState.READY, // Initial state is READY
	var pc: Long = 0,                    // Program Counter (starts at 0 by default)
	val registers: MutableMap<RegisterType, Long> = mutableMapOf(),  // Registers for this process
	var baseRegister: Long = 0,          // Base address of a process memory segment
	var limitRegister: Long = 0,         // Limit (size) of a process memory segment
	var stack: Stack = Stack(config.stackSize),
	var waitingPid: Long? = null,
)

enum class ProcessState {
	RUNNING, READY, BLOCKED, TERMINATED
}

