package internals.instructions.bitwise

import data.registers.RegisterType
import data.registers.read
import data.registers.write
import internals.Vm
import kotlin.test.Test
import kotlin.test.assertEquals

class BitwiseTest {
	val vm = Vm()
	val r1 = RegisterType.G1
	val r2 = RegisterType.G2
	val bitwise = Bitwise(vm)
	fun resetRs(a: Long, b: Long) {
		r1.write(vm, a)
		r2.write(vm, b)
	}

	init {
		resetRs(123L, 321L)
	}


	@Test
	fun `And Test General`() {
		bitwise.and(r1, r2, RegisterType.R3)
		assertEquals(65L, RegisterType.R3.read(vm))
	}


	@Test
	fun `Not Test General`() {
		bitwise.not(r1, RegisterType.R3)
		assertEquals(-124L, RegisterType.R3.read(vm))
	}

	@Test
	fun `Or Test General`() {
		bitwise.or(r1, r2, RegisterType.R3)
		assertEquals(379L, RegisterType.R3.read(vm))
	}

	@Test
	fun `Shift Left Test General`() {
		bitwise.shl(r1, r2, RegisterType.R3)
		assertEquals(246L, RegisterType.R3.read(vm))
	}

	@Test
	fun `Shift Right Test General`() {
		bitwise.shr(r1, r2, RegisterType.R3)
		assertEquals(61L, RegisterType.R3.read(vm))
	}

	@Suppress("DANGEROUS_CHARACTERS")
	@Test
	fun `Inverted Or? Test General`() {
		bitwise.xor(r1, r2, RegisterType.R3)
		assertEquals(314L, RegisterType.R3.read(vm))
	}


}