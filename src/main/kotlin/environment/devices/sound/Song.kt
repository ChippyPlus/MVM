package environment.devices.sound

class Song(vararg noteInit: Note) {
	val songArray = mutableListOf<Note>()
	private var index = 0

	init {
		for (note in noteInit) {
			songArray += note
		}
	}


	operator fun plusAssign(note: Note) {
		songArray.add(note)
	}

	override fun toString(): String = songArray.joinToString()


	fun show(): List<Note> = songArray

}
