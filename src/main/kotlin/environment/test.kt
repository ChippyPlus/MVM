package environment

data class Bit(val value: Int?)

class Memory(val name: String) {
	val m = mutableListOf<Bit>()
	var linkedR: IntRange? = null
	var linedRef: Memory? = null

	init {
		for (i in 0..100) {
			m.add(i, Bit(null))
		}
	}

	operator fun set(i: Int, value: Int?) {
		if (linkedR != null && linedRef != null && i in linkedR!!) {
			linedRef!!.m[i] = Bit(value)
		}
		m[i] = Bit(value)
	}

	operator fun get(i: Int): Bit {
		return m[i]
	}

	fun link(ref: Memory, range: IntRange) {
		linkedR = range
		linedRef = ref

		ref.linedRef = this
		ref.linkedR = range
	}

}


fun main() {
	val m1 = Memory("m1")
	val m2 = Memory("m2")
	m1.link(m2, 0..10)
	m2[0] = 20
	println(m1[0])
	println(m2[0])
}