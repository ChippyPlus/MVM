package engine.v2

import data.registers.enumIdenifiers.SuperRegisterType.*
import engine.execution.InstructData
import engine.parser
import org.junit.Assert.assertEquals
import java.io.File
import kotlin.test.Test


/*
arrayOf(InstructData(name=lit, values=arrayOf(G1, 5]), InstructData(name=str, values=arrayOf(R3, I love toes!!!]), InstructData(name=load, values=arrayOf(G1, R4]), InstructData(name=store, values=arrayOf(R4, G1]), InstructData(name=prints, values=arrayOf(]), InstructData(name=syscall, values=arrayOf(S0, S1, S2, S3]), InstructData(name=jz, values=arrayOf(1, R4]), InstructData(name=jnz, values=arrayOf(2, G2]), InstructData(name=jmp, values=arrayOf(3]), InstructData(name=printr, values=arrayOf(G1]), InstructData(name=push, values=arrayOf(S0]), InstructData(name=pop, values=arrayOf(R1]), InstructData(name=peek, values=arrayOf(G2]), InstructData(name=not, values=arrayOf(S1]), InstructData(name=strlen, values=arrayOf(R2]), InstructData(name=add, values=arrayOf(R1, S1]), InstructData(name=sub, values=arrayOf(G1, G4]), InstructData(name=mul, values=arrayOf(G3, S3]), InstructData(name=div, values=arrayOf(R1, G1]), InstructData(name=mod, values=arrayOf(G2, G3]), InstructData(name=mov, values=arrayOf(R1, G1]), InstructData(name=and, values=arrayOf(S0, S1]), InstructData(name=or, values=arrayOf(G1, G1]), InstructData(name=xor, values=arrayOf(R1, R3]), InstructData(name=shr, values=arrayOf(S0, S2]), InstructData(name=shl, values=arrayOf(S0, G3]), InstructData(name=strcmp, values=arrayOf(R3, S0]), InstructData(name=strcat, values=arrayOf(S1, G2]), InstructData(name=strcpy, values=arrayOf(G3, R1])]

 */
class ParseTest {
    @Test
    fun `Test the parser`() {
        val f = File("src/test/resources/tokeniseTest.kar")
        val x: List<InstructData> = parser(f)
        val expected = arrayListOf(
            InstructData(name = "lit", values = arrayOf(G1, 5)),
            InstructData(name = "str", values = arrayOf(R3, "I love toes!!!")),
            InstructData(name = "load", values = arrayOf(G1, R4)),
            InstructData(name = "store", values = arrayOf(R4, G1)),
            InstructData(name = "prints", values = arrayOf()),
            InstructData(name = "syscall", values = arrayOf(S0, S1, S2, S3)),
            InstructData(name = "jz", values = arrayOf(1, R4)),
            InstructData(name = "jnz", values = arrayOf(2, G2)),
            InstructData(name = "jmp", values = arrayOf(3)),
            InstructData(name = "printr", values = arrayOf(G1)),
            InstructData(name = "push", values = arrayOf(S0)),
            InstructData(name = "pop", values = arrayOf(R1)),
            InstructData(name = "peek", values = arrayOf(G2)),
            InstructData(name = "not", values = arrayOf(S1)),
            InstructData(name = "strlen", values = arrayOf(R2)),
            InstructData(name = "per instruction test/add", values = arrayOf(R1, S1)),
            InstructData(name = "sub", values = arrayOf(G1, G4)),
            InstructData(name = "mul", values = arrayOf(G3, S3)),
            InstructData(name = "div", values = arrayOf(R1, G1)),
            InstructData(name = "mod", values = arrayOf(G2, G3)),
            InstructData(name = "mov", values = arrayOf(R1, G1)),
            InstructData(name = "and", values = arrayOf(S0, S1)),
            InstructData(name = "or", values = arrayOf(G1, G1)),
            InstructData(name = "xor", values = arrayOf(R1, R3)),
            InstructData(name = "shr", values = arrayOf(S0, S2)),
            InstructData(name = "shl", values = arrayOf(S0, G3)),
            InstructData(name = "strcmp", values = arrayOf(R3, S0)),
            InstructData(name = "strcat", values = arrayOf(S1, G2)),
            InstructData(name = "strcpy", values = arrayOf(G3, R1))
        ).toList()
        assertEquals(expected.toString(), x.toString())
    }
}