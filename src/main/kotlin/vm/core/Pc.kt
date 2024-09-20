package vm.core

class Pc {
    private var pc: ULong = 0uL
    fun increment() = pc++

    fun decrement() = pc--

    fun update(value: ULong) {
        pc = value
    }

}