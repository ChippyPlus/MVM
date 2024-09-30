package engine.v2

import engine.parser
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class ParseInstructionSingleTest {


    @Test
    fun `Parse Single LIT  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/lit")
        ).toString()
        assertEquals("[InstructData(name=lit, values=[G1, 5])]", p)
    }

    @Test
    fun `Parse Single STR  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/str")
        ).toString()
        assertEquals("[InstructData(name=str, values=[R3, I love toes!!!])]", p)
    }

    @Test
    fun `Parse Single LOAD  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/load")
        ).toString()
        assertEquals("[InstructData(name=load, values=[G1, R4])]", p)
    }

    @Test
    fun `Parse Single STORE  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/store")
        ).toString()
        assertEquals("[InstructData(name=store, values=[R4, G1])]", p)
    }

    @Test
    fun `Parse Single PRINTS  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/prints")
        ).toString()
        assertEquals("[InstructData(name=prints, values=[])]", p)
    }

    @Test
    fun `Parse Single SYSCALL  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/syscall")
        ).toString()
        assertEquals("[InstructData(name=syscall, values=[S1, S2, S3, S4])]", p)
    }

    @Test
    fun `Parse Single JZ  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/jz")
        ).toString()
        assertEquals("[InstructData(name=jz, values=[1, R4])]", p)
    }

    @Test
    fun `Parse Single JNZ  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/jnz")
        ).toString()
        assertEquals("[InstructData(name=jnz, values=[2, G2])]", p)
    }

    @Test
    fun `Parse Single JMP  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/jmp")
        ).toString()
        assertEquals("[InstructData(name=jmp, values=[3])]", p)
    }

    @Test
    fun `Parse Single PRINTR  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/printr")
        ).toString()
        assertEquals("[InstructData(name=printr, values=[G1])]", p)
    }

    @Test
    fun `Parse Single PUSH  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/push")
        ).toString()
        assertEquals("[InstructData(name=push, values=[S1])]", p)
    }

    @Test
    fun `Parse Single POP  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/pop")
        ).toString()
        assertEquals("[InstructData(name=pop, values=[R1])]", p)
    }

    @Test
    fun `Parse Single PEEK  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/peek")
        ).toString()
        assertEquals("[InstructData(name=peek, values=[G2])]", p)
    }

    @Test
    fun `Parse Single NOT  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/not")
        ).toString()
        assertEquals("[InstructData(name=not, values=[S2])]", p)
    }

    @Test
    fun `Parse Single STRLEN  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/strlen")
        ).toString()
        assertEquals("[InstructData(name=strlen, values=[R2])]", p)
    }

    @Test
    fun `Parse Single ADD  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/add")
        ).toString()
        assertEquals("[InstructData(name=add, values=[R1, S2])]", p)
    }

    @Test
    fun `Parse Single SUB  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/sub")
        ).toString()
        assertEquals("[InstructData(name=sub, values=[G1, G4])]", p)
    }

    @Test
    fun `Parse Single MUL  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/mul")
        ).toString()
        assertEquals("[InstructData(name=mul, values=[G3, S4])]", p)
    }

    @Test
    fun `Parse Single DIV  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/div")
        ).toString()
        assertEquals("[InstructData(name=div, values=[R1, G1])]", p)
    }

    @Test
    fun `Parse Single MOD  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/mod")
        ).toString()
        assertEquals("[InstructData(name=mod, values=[G2, G3])]", p)
    }

    @Test
    fun `Parse Single MOV  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/mov")
        ).toString()
        assertEquals("[InstructData(name=mov, values=[R1, G1])]", p)
    }

    @Test
    fun `Parse Single AND  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/and")
        ).toString()
        assertEquals("[InstructData(name=and, values=[S1, S2])]", p)
    }

    @Test
    fun `Parse Single OR  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/or")
        ).toString()
        assertEquals("[InstructData(name=or, values=[G1, G1])]", p)
    }

    @Test
    fun `Parse Single XOR  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/xor")
        ).toString()
        assertEquals("[InstructData(name=xor, values=[R1, R3])]", p)
    }

    @Test
    fun `Parse Single SHR  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/shr")
        ).toString()
        assertEquals("[InstructData(name=shr, values=[S1, S3])]", p)
    }

    @Test
    fun `Parse Single SHL  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/shl")
        ).toString()
        assertEquals("[InstructData(name=shl, values=[S1, G3])]", p)
    }

    @Test
    fun `Parse Single STRCMP  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/strcmp")
        ).toString()
        assertEquals("[InstructData(name=strcmp, values=[R3, S1])]", p)
    }

    @Test
    fun `Parse Single STRCAT  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/strcat")
        ).toString()
        assertEquals("[InstructData(name=strcat, values=[S2, G2])]", p)
    }

    @Test
    fun `Parse Single STRCPY  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/strcpy")
        ).toString()
        assertEquals("[InstructData(name=strcpy, values=[G3, R1])]", p)
    }
}