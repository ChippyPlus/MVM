package environment.devices.sound


class Samples {
	class Samples {
		val ascendingScale = Song(Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.B)
		val simpleStaccato = Song(Note.C, Note.C, Note.D, Note.D, Note.E, Note.E)
		val descendingScale = Song(Note.G, Note.F, Note.E, Note.D, Note.C)
		val simpleArpeggio = Song(Note.C, Note.E, Note.G, Note.C)
		val minorArpeggio = Song(Note.D, Note.F, Note.A, Note.C)
		val majorArpeggio = Song(Note.E, Note.G, Note.B, Note.D)
		val minorSeventhChord = Song(Note.F, Note.A, Note.C, Note.E)
		val majorSeventhChord = Song(Note.G, Note.B, Note.D, Note.F)
		val minorNinthChord = Song(Note.A, Note.C, Note.E, Note.G)
		val majorNinthChord = Song(Note.B, Note.D, Note.F, Note.A)
		val descendingChromaticScale =
			Song(Note.C, Note.D, Note.E, Note.F, Note.G, Note.F, Note.E, Note.D, Note.C)
		val ascendingChromaticScale =
			Song(Note.C, Note.D, Note.E, Note.F, Note.G, Note.G, Note.F, Note.E, Note.D, Note.C)
		val chromaticScaleUpAndDown =
			Song(Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.G, Note.F, Note.E, Note.D, Note.C)
		val wholeToneScale = Song(
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.A,
			Note.B,
			Note.A,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C
		)
		val repeatedNote = Song(
			Note.C,
			Note.C,
			Note.D,
			Note.D,
			Note.E,
			Note.E,
			Note.F,
			Note.F,
			Note.G,
			Note.G,
			Note.A,
			Note.A,
			Note.B,
			Note.B
		)
		val descendingChromaticWithRepeatedNotes = Song(
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.A,
			Note.B,
			Note.C,
			Note.B,
			Note.A,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C
		)
		val descendingChromaticWithTriplets = Song(
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.A,
			Note.B,
			Note.C,
			Note.C,
			Note.B,
			Note.A,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C
		)
		val descendingChromaticWithQuadruplets = Song(
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.A,
			Note.B,
			Note.C,
			Note.C,
			Note.C,
			Note.B,
			Note.A,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C
		)
		val ascendingAndDescendingScales = Song(
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.A,
			Note.B,
			Note.C,
			Note.D,
			Note.C,
			Note.B,
			Note.A,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C
		)
		val fullChromaticScaleUpAndDown = Song(
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.A,
			Note.B,
			Note.C,
			Note.D,
			Note.E,
			Note.F,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C,
			Note.B,
			Note.A,
			Note.G,
			Note.F,
			Note.E,
			Note.D,
			Note.C
		)
	}
}