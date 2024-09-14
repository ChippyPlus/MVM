package org.example.dog


import internals.instructions.arithmetic.add
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite
import org.example.kvm
import org.example.returnRegisters
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun main() {
    fullRegisterWrite(SuperRegisterType.G1, 10L)
    fullRegisterWrite(SuperRegisterType.G2, 5L)
    kvm.arithmetic.add(SuperRegisterType.G1, SuperRegisterType.G2)
    if (returnRegisters.r4 != 15L) {
        error("Test failed!!!")
    } else {
        println("YAY")
    }
}

class DogTest {
    @Test
    fun `Add 1 and 1`() {
        val x = 1 + 1
        assertEquals(x, 2)
    }

    @Test
    fun `Add 1 and 2`() {
        val x = 1 + 2
        assertEquals(x, 3)
    }
}

