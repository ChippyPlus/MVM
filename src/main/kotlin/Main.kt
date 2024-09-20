import engine.Parser
import vm.Mvm
import java.io.File


val mvm = Mvm()

fun main(args: Array<String>) {
    val f = File("main.kar").readLines()
    Parser(f)
}