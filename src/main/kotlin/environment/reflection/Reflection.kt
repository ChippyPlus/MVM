package environment.reflection


val reflection = Reflection()

class Reflection {
	val currentFileData = CurrentFileData()
	val vmTracker = mutableListOf<KProcess>()
}