package environment

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import taskManager


class TaskManager {
	private val startTime = System.currentTimeMillis()
	private val taskChannel = Channel<suspend () -> Unit>() // Channel for Unit-returning functions
	private val taskScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
	private lateinit var taskManager: Job
	private val activeTasks = mutableListOf<Job>()  // Keep track of active tasks

	init {
		println("Main thread doing other work...\n----------------\n")
	}


	fun addTask(block: suspend () -> Unit) {
		taskScope.launch { taskChannel.send(block) }
		if (!::taskManager.isInitialized) { //Start the task manager only once.
			taskManager = taskScope.launch {
				for (task in taskChannel) {
					val taskJob = launch { task() } // Launch each task concurrently
					activeTasks.add(taskJob) // Track tasks for waiting. Could add in joinTo.
				}
				activeTasks.joinAll() // Wait for all tasks to complete
				println("All tasks completed from the task manager perspective.")
			}
		}


	}


	suspend fun wait() {
		taskChannel.close()
		taskManager.join()
		println("\n----------------\nAll tasks completed. Main exiting.")
		val endTime = System.currentTimeMillis()
		println("Total time taken: ${endTime - startTime}ms")
	}
}


fun main() = runBlocking {

	println("Main thread doing other work...")
	Thread.sleep(500)
	println("Main thread continuing...")

	taskManager.addTask { f(1, 2000) }
	taskManager.addTask { f(2, 3000) }
	taskManager.addTask { f(3, 1000) }


	launch { taskManager.wait() }

	println("Main thread still working")


}


suspend fun f(index: Int, time: Long) {
	delay(time)
	println("Finished task $index on thread ${Thread.currentThread().name}")
}

