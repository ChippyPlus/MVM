package org.example.dog

fun Int.toOByte(radix: Int = 10): Byte = this.toInt().toByte()

fun main(){

    val x:Byte = (128).toOByte()
//    x++
    println(x)

}