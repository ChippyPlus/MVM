package org.example.dog


enum class RGB {
    Red,
    Green,
    Blue;
    fun dog() {
        println("ok")
    }
}

fun main() {
    val x = RGB.Green
    println(RGB.Blue)
}