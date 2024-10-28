package environment.devices.sound


class Samples {
	class Samples {
		val ascendingScale = Song(Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.A, Notes.B)
		val simpleStaccato = Song(Notes.C, Notes.C, Notes.D, Notes.D, Notes.E, Notes.E)
		val descendingScale = Song(Notes.G, Notes.F, Notes.E, Notes.D, Notes.C)
		val simpleArpeggio = Song(Notes.C, Notes.E, Notes.G, Notes.C)
		val minorArpeggio = Song(Notes.D, Notes.F, Notes.A, Notes.C)
		val majorArpeggio = Song(Notes.E, Notes.G, Notes.B, Notes.D)
		val minorSeventhChord = Song(Notes.F, Notes.A, Notes.C, Notes.E)
		val majorSeventhChord = Song(Notes.G, Notes.B, Notes.D, Notes.F)
		val minorNinthChord = Song(Notes.A, Notes.C, Notes.E, Notes.G)
		val majorNinthChord = Song(Notes.B, Notes.D, Notes.F, Notes.A)
		val descendingChromaticScale =
			Song(Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.F, Notes.E, Notes.D, Notes.C)
		val ascendingChromaticScale =
			Song(Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.G, Notes.F, Notes.E, Notes.D, Notes.C)
		val chromaticScaleUpAndDown =
			Song(Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.A, Notes.G, Notes.F, Notes.E, Notes.D, Notes.C)
		val wholeToneScale = Song(
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.A,
			Notes.B,
			Notes.A,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C
		)
		val repeatedNotes = Song(
			Notes.C,
			Notes.C,
			Notes.D,
			Notes.D,
			Notes.E,
			Notes.E,
			Notes.F,
			Notes.F,
			Notes.G,
			Notes.G,
			Notes.A,
			Notes.A,
			Notes.B,
			Notes.B
		)
		val descendingChromaticWithRepeatedNotes = Song(
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.A,
			Notes.B,
			Notes.C,
			Notes.B,
			Notes.A,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C
		)
		val descendingChromaticWithTriplets = Song(
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.A,
			Notes.B,
			Notes.C,
			Notes.C,
			Notes.B,
			Notes.A,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C
		)
		val descendingChromaticWithQuadruplets = Song(
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.A,
			Notes.B,
			Notes.C,
			Notes.C,
			Notes.C,
			Notes.B,
			Notes.A,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C
		)
		val ascendingAndDescendingScales = Song(
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.A,
			Notes.B,
			Notes.C,
			Notes.D,
			Notes.C,
			Notes.B,
			Notes.A,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C
		)
		val fullChromaticScaleUpAndDown = Song(
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.A,
			Notes.B,
			Notes.C,
			Notes.D,
			Notes.E,
			Notes.F,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C,
			Notes.B,
			Notes.A,
			Notes.G,
			Notes.F,
			Notes.E,
			Notes.D,
			Notes.C
		)
	}
}