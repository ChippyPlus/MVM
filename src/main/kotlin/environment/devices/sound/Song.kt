package environment.devices.sound

class Song(vararg notesInit: Notes) {
	val songArray = mutableListOf<Notes>()

	init {
		for (note in notesInit) {
			songArray += note
		}
	}

	operator fun plusAssign(note: Notes) {
		songArray.add(note)
	}

	override fun toString(): String = songArray.joinToString()


	fun show(): List<Notes> = songArray

}
