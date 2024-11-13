package test

import kotlinx.coroutines.*


suspend fun sleep(x: Int) {
	delay(1000)
	println("${Thread.currentThread().name} : $x")
}


@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main(): Unit = runBlocking {
	for (i in 1..10) {
		launch(newSingleThreadContext("vm=$i")) {
			sleep(i)
		}
	}
}