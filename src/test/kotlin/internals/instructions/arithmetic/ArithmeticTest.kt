package internals.instructions.arithmetic

import data.registers.*
import internals.Vm
import org.junit.Test
import kotlin.test.assertEquals


class ArithmeticTest {
	val vm = Vm()
	val r1 = RegisterType.G1
	val r2 = RegisterType.G2
	val arithmetic = Arithmetic(vm)

	init {
		r1.write(vm, 10)
		r2.write(vm, 5)
	}

	@Test
	fun `Addition Test General`() {
		arithmetic.add(r1, r2)
		assertEquals(15L, RegisterType.R4.read(vm))
	}

	@Test
	fun `Subtraction Test General`() {
		arithmetic.sub(r1, r2)
		assertEquals(5L, RegisterType.R4.read(vm))
	}


	@Test
	fun `Multiplication Test General`() {
		arithmetic.mul(r1, r2)
		assertEquals(50L, RegisterType.R4.read(vm))
	}

	@Test
	fun `Division Test General`() {
		arithmetic.div(r1, r2)
		assertEquals(2L, RegisterType.R4.read(vm))
	}

	@Test
	fun `Modulus Test General`() {
		arithmetic.mod(r1, r2)
		assertEquals(0L, RegisterType.R4.read(vm))
	}

	@Test
	fun `Greater Than Test if positive`() {
		r1.write(vm, 5)
		r2.write(vm, 10)
		arithmetic.gt(r1, r2)
		val out = intelNames[IntelRegisters.GF].read(vm)
		assertEquals(0L, out)
	}

	@Test
	fun `Greater Than Test if negative`() {
		r1.write(vm, 10)
		r2.write(vm, 5)
		arithmetic.gt(r1, r2)
		val out = intelNames[IntelRegisters.GF].read(vm)
		assertEquals(1L, out)
	}


	@Test
	fun `Less Than Test if positive`() {
		r1.write(vm, 10)
		r2.write(vm, 5)
		arithmetic.lt(r1, r2)
		val out = intelNames[IntelRegisters.GF].read(vm)
		assertEquals(0L, out)

	}

	@Test
	fun `Less Than Test if negative`() {
		r1.write(vm, 5)
		r2.write(vm, 10)
		arithmetic.lt(r1, r2)
		val out = intelNames[IntelRegisters.GF].read(vm)
		assertEquals(1L, out)
	}


	@Test
	fun `Equal Test if positive`() {
		r2.write(vm, 10)
		r1.write(vm, 10)
		arithmetic.eq(r1, r2)
		val out = intelNames[IntelRegisters.EF].read(vm)
		assertEquals(1L, out)
	}


	@Test
	fun `Equal Test if negative`() {
		r2.write(vm, 5)
		r1.write(vm, 10)
		arithmetic.eq(r1, r2)
		val out = intelNames[IntelRegisters.EF].read(vm)
		assertEquals(0L, out)
	}


	@Test
	fun `Powers Test General`() {
		r2.write(vm, 4)
		r1.write(vm, 2)
		arithmetic.pow(r1, r2)
		assertEquals(16L, RegisterType.R4.read(vm))

	}

}