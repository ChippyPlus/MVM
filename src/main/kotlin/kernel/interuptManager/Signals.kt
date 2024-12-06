package kernel.interuptManager

enum class Signals(val code: Int, val signalName: String) {
	InterruptProgram(code = 2, signalName = "INT"), EmulateInstructionExecuted(
		code = 7, signalName = "EMT"
	),
	AlarmTimer(code = 14, signalName = "ALRM"), Termination(
		code = 15, signalName = "TERM"
	),
	UrgentCondition(
		code = 16, signalName = "URG"
	),
	Stop(
		code = 18, signalName = "TSTP"
	),
	Continue(code = 19, signalName = "CONT"), ChildStatusChanged(
		code = 20, signalName = "CHLD"
	),
	BackgroundTerminalReadAttempt(code = 21, signalName = "TTIN"), BackgroundTerminalWriteAttempt(
		code = 22, signalName = "TTOU"
	),
	CpuTimeExceeded(code = 24, signalName = "XCPU"), FileSizeExceeded(code = 25, signalName = "XFSZ"), VirtualTimeAlarm(
		code = 26, signalName = "VTALRM"
	),
	ProfilingTimerAlarm(code = 27, signalName = "PROF"), WindowSizeChange(
		code = 28, signalName = "WINCH"
	),
	StatusRequestFromKeyboard(code = 29, signalName = "INFO"), UserDefinedOne(
		code = 30, signalName = "USR1"
	),
	UserDefinedTwo(code = 31, signalName = "USR2")
}