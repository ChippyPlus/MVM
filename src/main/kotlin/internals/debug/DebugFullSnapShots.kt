package internals.debug

import data.registers.RegisterData
import data.registers.RegisterType
import environment.reflection.reflection
import internals.Vm
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class DebugRegisters(val fileName: String, val pc: Long, val registers: Map<RegisterType, RegisterData>)


class DebugFullSnapShots {
	init {
		File("debug/register snapshots").deleteRecursively()
		File("debug/register snapshots").mkdir()
	}

	private fun collect(vm: Vm): DebugRegisters {
		return DebugRegisters(
			reflection.groupTrackedVmByVm()[vm]!!.file.name, vm.pc, vm.registers.registers
		)
	}


	private val json = Json { prettyPrint = true }

	private fun write(collected: DebugRegisters) {
		val string = json.encodeToString(collected)
		File("debug/register snapshots/${collected.pc}.json").createNewFile()
		File("debug/register snapshots/${collected.pc}.json").writeText(string)
	}

	fun act(vm: Vm) = write(collect(vm))

}