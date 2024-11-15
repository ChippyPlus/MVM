package internals.instructions.xFloats

import data.registers.RegisterType.*
import data.registers.read
import data.registers.write
import helpers.toDoubleOrFloatBasedOnDataType
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

	@Test
	fun `X Addition Test General`() {
		xf.xLit(X1, "4.395".toDoubleOrFloatBasedOnDataType(vm, X1))
		xf.xLit(X2, "6.134".toDoubleOrFloatBasedOnDataType(vm, X2))
		xf.xAdd(X1, X2)
		assertEquals(1093170944, vm.registers.read(R5))
	}

	@Test
	fun `X Subtraction Test General`() {
		xf.xLit(X1, "23.134".toDoubleOrFloatBasedOnDataType(vm, X1))
		xf.xLit(X2, "-9.234".toDoubleOrFloatBasedOnDataType(vm, X2))
		xf.xSub(X1, X2)
		assertEquals(1107392768, vm.registers.read(R5))
	}


	@Test
	fun `X Multiplication Test General`() {
		xf.xLit(X1, "5.234".toDoubleOrFloatBasedOnDataType(vm, X1))
		xf.xLit(X2, "-9.234".toDoubleOrFloatBasedOnDataType(vm, X2))
		xf.xMul(X1, X2)
		assertEquals(-1035906368, vm.registers.read(R5))
	}

	@Test
	fun `X Division Test General`() {
		xf.xLit(X1, "100.1234".toDoubleOrFloatBasedOnDataType(vm, X1))
		xf.xLit(X2, "5.321".toDoubleOrFloatBasedOnDataType(vm, X2))
		xf.xMul(X1, X2)
		assertEquals(1141190656, vm.registers.read(R5))
	}
}