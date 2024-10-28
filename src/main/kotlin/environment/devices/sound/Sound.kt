package environment.devices.sound

import environment.sound.sampleRate
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.math.sin

class Sound {
	fun play(frequency: Float, duration: Float) {
		val sleepTime = (duration * 1000).toLong()
		val byteArrayOutputStream = ByteArrayOutputStream()

		var i = 0
		while (i < sampleRate * duration) {
			val angle = (2.0 * Math.PI * i * frequency) / sampleRate
			val sample = sin(angle)

			val byteSample = (sample * 127).toInt().toByte()
			byteArrayOutputStream.write(byteSample.toInt())
			i++
		}

		val audioData: ByteArray = byteArrayOutputStream.toByteArray()
		val audioFormat = AudioFormat(sampleRate.toFloat(), 8, 1, true, false)
		val clip: Clip = AudioSystem.getClip()



		clip.open(audioFormat, audioData, 0, audioData.size)

		clip.start()
		Thread.sleep(sleepTime / 2)
		clip.stop()

		runBlocking {
			launch {
				Thread.sleep(sleepTime / 2)
				clip.close()
			}
		}
	}
}