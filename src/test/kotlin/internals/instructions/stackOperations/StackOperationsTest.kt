package internals.instructions.stackOperations

import data.registers.RegisterType
import data.registers.read
import data.registers.write
import internals.Vm
import kotlin.test.Test
import kotlin.test.assertEquals

class StackOperationsTest {
	val vm = Vm()
	val so = vm.stackOperations

	@Test
	fun `Push Test General`() {
		RegisterType.G1.write(vm, 15)
		so.push(RegisterType.G1)
		assertEquals(15, so.internalStack.inspect().first())
	}

	@Test
	fun `Push Literal Test General`() {
		so.pushl(354)
		assertEquals(354, so.internalStack.inspect().first())
	}

	@Test
	fun `Peek Test General`() {
		so.internalStack.push(99)
		so.peek(RegisterType.G3)
		assertEquals(99, RegisterType.G3.read(vm))
		assertEquals(99, so.internalStack.inspect().first())
	}

	@Test
	fun `Pop Test General`() {
		so.internalStack.push(445)
		so.pop(RegisterType.G3)
		assertEquals(445, RegisterType.G3.read(vm))
		println(so.internalStack.inspect().joinToString())
		assertEquals(true, so.internalStack.isEmpty())
	}
}