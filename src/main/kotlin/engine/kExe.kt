package engine
//
//import engine.execution.Execute
//import environment.snapShotManager
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.lang.Thread.sleep
//
//
//var pc1 = 0
//var pc2 = 0
//val exe = Execute()
//var depends = true
//val clock = Channel()
//val ps = listOf(p1, p2)
//val cJob = Job()
//var end = false
//
//
//var p1r = snapShotManager.snapShotRegisters()
//var p2r = snapShotManager.snapShotRegisters()
//suspend fun main() {
//	val max = ps.map { it.size }.toIntArray().max()
//	clock()
//
//	while (true) {
//		if (end) break
//
//		withContext(cJob) {
//			if (clock.i % 10 == 0) {
//				if (depends && pc1 < p1.size) {
//					snapShotManager.populateSnapShotRegister(p1r)
//					exe.exeWhen(p1[pc1].name, p1[pc1].values)
//					p1r = snapShotManager.snapShotRegisters()
//					snapShotManager.populateSnapShotRegister(p1r)
//					pc1++
//				} else if (!depends && pc2 < p2.size) {
//					snapShotManager.populateSnapShotRegister(p2r)
//					exe.exeWhen(p2[pc2].name, p2[pc2].values)
//					p2r = snapShotManager.snapShotRegisters()
//					snapShotManager.populateSnapShotRegister(p2r)
//					pc2++
//				} else if (pc1 == p1.size && pc2 == p2.size) {
//					println("Complete")
//					end = true
//				}
//
//				depends = !depends
//			}
//		}
//	}
//}
//
//
//fun clock() {
//	CoroutineScope(cJob).launch {
//		while (true) {
//			if (end) break
//			clock.increment()
//			sleep(10)
//		}
//	}
//}
//
//
//class Channel {
//	var i = 0
//	fun increment() {
//		i += 1
//	}
//}
//
//
