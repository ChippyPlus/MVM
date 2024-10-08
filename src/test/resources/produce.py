x = """LIT G1 5
STR R3 "I love toes!!!"
LOAD G1 R4
STORE R4 G1
PRINTS
SYSCALL
JZ 1 R4
JNZ 2 G2
JMP 3
PRINTR G1
PUSH S1
POP R1
PEEK G2
NOT S2
STRLEN R2
ADD R1 S2
SUB G1 G4
MUL G3 S4
DIV R1 G1
MOD G2 G3
MOV R1 G1
AND S1 S2
OR G1 G1
XOR R1 R3
SHR S1 S3
SHL S1 G3
STRCMP R3 S1
STRCAT S2 G2
STRCPY G3 R1""".split('\n')

y = """InstructData(name=lit, values=[G1, 5])
InstructData(name=str, values=[R3, I love toes!!!])
InstructData(name=load, values=[G1, R4])
InstructData(name=store, values=[R4, G1])
InstructData(name=prints, values=[])
InstructData(name=syscall, values=[S1, S2, S3, S4])
InstructData(name=jz, values=[1, R4])
InstructData(name=jnz, values=[2, G2])
InstructData(name=jmp, values=[3])
InstructData(name=printr, values=[G1])
InstructData(name=push, values=[S1])
InstructData(name=pop, values=[R1])
InstructData(name=peek, values=[G2])
InstructData(name=not, values=[S2])
InstructData(name=strlen, values=[R2])
InstructData(name=per instruction test/add, values=[R1, S2])
InstructData(name=sub, values=[G1, G4])
InstructData(name=mul, values=[G3, S4])
InstructData(name=div, values=[R1, G1])
InstructData(name=mod, values=[G2, G3])
InstructData(name=mov, values=[R1, G1])
InstructData(name=and, values=[S1, S2])
InstructData(name=or, values=[G1, G1])
InstructData(name=xor, values=[R1, R3])
InstructData(name=shr, values=[S1, S3])
InstructData(name=shl, values=[S1, G3])
InstructData(name=strcmp, values=[R3, S1])
InstructData(name=strcat, values=[S2, G2])
InstructData(name=strcpy, values=[G3, R1])
""".split('\n')

tmplate = """@Test
    fun `Parse Single LIT instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/lit")
        ).toString()
        assertEquals("InstructData(name=lit, values=[G1, 5])", p)
    }"""


def fmt(name, instruct):
    return """\n@Test
    fun `Parse Single """ + name.upper() + """  instruction Test`() {
        val p = parser(
            File("src/test/resources/per instruction test/""" + name + """")
        ).toString()
        assertEquals(\"[""" + instruct + """]\", p)
    }"""

index = -1
for i in x:
    index +=1
    # noinspection PyUnresolvedReferences
    with open("/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/src/test/kotlin/engine/v2/ParseInstructionSingleTest.kt",'a') as f:
        f.write(fmt(i.split()[0].lower(),y[index]))