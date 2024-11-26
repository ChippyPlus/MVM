package environment

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

@Deprecated("This wasn't meant to use kotlinx coroutines 😭😭. Like a time sharing os!!!")
private class TaskManager {
	private val taskChannel = Channel<suspend () -> Unit>() // Channel for Unit-returning functions
	private val taskScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
	private lateinit var taskManager: Job
	private val activeTasks = mutableListOf<Job>()  // Keep track of active tasks


	@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
	fun addTask(block: suspend () -> Unit) {
		taskScope.launch { taskChannel.send(block) }
		if (!::taskManager.isInitialized) {
			taskManager = taskScope.launch {
				for (task in taskChannel) {
					val taskJob =
						launch(newSingleThreadContext("Kotlin's slaves \$${activeTasks.size + 1}")) { task() } // Launch each task concurrently
					activeTasks.add(taskJob)
				}
				activeTasks.joinAll()
			}
		}


	}


	suspend fun wait() {
		if (::taskManager.isInitialized) {
			taskChannel.close()
			taskManager.join()
		}
	}
}


fun main() = runBlocking {

	println("Main thread doing other work...")
	Thread.sleep(500)
	println("Main thread continuing...")




	println("Main thread still working")


}


private suspend fun f(index: Int, time: Long) {
	delay(time)
	println("Finished task $index on thread ${Thread.currentThread().name}")
}

