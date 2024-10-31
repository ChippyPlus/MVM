package engine.parseEngine

import errors
import kotlin.system.exitProcess


fun Parse.useInstruct(tokens: TokenList): List<TokenData> {
	val out = mutableListOf<TokenData>()
	for (line in tokens) {
		out.add(
			when (instructionBlueprints[line[0]]) {
				InstructType.Ignore -> InstructBuild(line).ignore()
				InstructType.Register -> InstructBuild(line).register()
				InstructType.RegisterRegister -> InstructBuild(line).registerRegister()
				InstructType.RegisterLong -> InstructBuild(line).registerLong()
				InstructType.Long -> InstructBuild(line).long()
				InstructType.LongRegister -> InstructBuild(line).longRegister()
				InstructType.SingleString -> InstructBuild(line).singleString()
				InstructType.StrSpecific -> InstructBuild(line).strSpecific()
				InstructType.None -> InstructBuild(line).none()
				InstructType.Settype -> InstructBuild(line).settype()
				InstructType.Xlit -> InstructBuild(line).xlit()
				InstructType.SystemCall -> InstructBuild(line).systemCall()
				null -> {
					errors.InvalidInstructionException(line[0])
					exitProcess(1)
				}
			}
		)
	}
	return out.toList()
}