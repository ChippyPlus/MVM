package environment.interuptManager

enum class Signals(code: Int) {
	InterruptProgram(code = 2), EmulateInstructionExecuted(code = 7), AlarmTimer(code = 14), Termination(code = 15), UrgentCondition(
		code = 16
	),
	Stop(code = 18), Continue(code = 19), ChildStatusChanged(code = 20), BackgroundTerminalReadAttempt(code = 21), BackgroundTerminalWriteAttempt(
		code = 22
	),
	CpuTimeExceeded(code = 24), FileSizeExceeded(code = 25), VirtualTimeAlarm(code = 26), ProfilingTimerAlarm(code = 27), WindowSizeChange(
		code = 28
	),
	StatusRequestFromKeyboard(code = 29), UserDefinedOne(code = 30), UserDefinedTwo(code = 31)
}