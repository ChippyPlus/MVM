package optimisations

import engine.parseEngine.Parse
import java.io.File
import kotlin.math.pow

fun checkBase2(n: Long): Int? {
	var index = 0
	var no: Long
	while (true) {
		index++
		no = 2.0.pow(index).toLong()
		if (no == n) return index
		else if (no > n) return null
	}
}


val f = Parse(File("main.mar")).tokenise()


fun main() {
	val o = Optimisations(f)
	val inp = o.insertValuesLit().toMutableList()

	inp.forEach(::println)
}


