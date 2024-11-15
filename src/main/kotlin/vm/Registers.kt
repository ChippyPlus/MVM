package vm

import processes.Pcb
import vm.exceptions.VmExceptions.InvalidRegisterException


enum class RegisterType { G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, X1, X2, X3, X4, X5, X6, X7, X8, X9, X10, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10 }


class Registers {
	private val registers = mutableMapOf<RegisterType, Long>()

	fun initializeRegisters(pcb: Pcb) {
		for (register in RegisterType.entries) {
			pcb.registers[register] = 0 // Initialise all registers to 0 for this process
		}
		setPC(pcb, 0) // Initial PC value
	}


	fun read(register: RegisterType, pcb: Pcb): Long {
		return pcb.registers[register] ?: throw InvalidRegisterException("Invalid register: $register")
	}

	fun write(register: RegisterType, value: Long, pcb: Pcb) {

		if (register == RegisterType.I8) {
			setPC(pcb = pcb, value = value)
		}
		pcb.registers[register] = value
	}

	fun getPC(pcb: Pcb): Long = pcb.pc

	fun setPC(pcb: Pcb, value: Long) {
		pcb.pc = value
	}


}