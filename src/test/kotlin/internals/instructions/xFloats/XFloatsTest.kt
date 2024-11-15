package internals.instructions.xFloats

import data.registers.RegisterType.G2
import data.registers.RegisterType.X1
import data.registers.read
import data.registers.write
import internals.Vm
import kotlin.test.Test
import kotlin.test.assertEquals

class XFloatsTest {
	val vm = Vm()
	val xf = XFloats(vm)

	@Test
	fun `Float to Int Test General`() {
		X1.write(vm, 22.145f.toBits().toLong())
		xf.ftoi(X1, G2)
		assertEquals(22, G2.read(vm))
	}


	@Test
	fun `Int to Float Test General`() {
		G2.write(vm, 22)
		xf.itof(G2, X1)
		assertEquals(22.0f, Float.fromBits(X1.read(vm).toInt()))
	}

}