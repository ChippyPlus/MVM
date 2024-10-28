package environment.devices.sound


enum class Note(val frequency: Float) {
	A(440.0f), ASharp(466.16f), B(493.88f), C(523.25f), CSharp(554.37f), D(587.33f), DSharp(622.25f), E(659.26f), F(
		698.46f
	),
	FSharp(739.99f), G(783.99f), GSharp(830.61f);


}

val noteIndexes = run {
	val out = mutableMapOf<Int, Note>()
	for (i in Note.entries.withIndex()) {
		out[i.index] = i.value
	}
	out
}