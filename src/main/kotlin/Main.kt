import data.registers.Registers
import engine.Parser
import engine.runner
import enviroment.Errors
import vm.Mvm
import java.io.File


val mvm = Mvm()
val errors = Errors()
val registers = Registers()
fun main(args: Array<String>) {
    val f = File("main.kar").readLines()
    val pr = Parser(f)
    for (i in pr) {
        runner(i)
    }
}