package environment.devices.sound

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl
import kotlin.math.sin

const val sampleRate = 44100 * 1

class Sound {
	private var masterVolume = 0.8f

	fun setVolume(volume: Float) {
		masterVolume = volume.coerceIn(0.0f, 1.0f)
	}

	fun play(frequency: Double, duration: Float = 0.5f) {


		val sleepTime = (duration * 1000).toLong()
		val byteArrayOutputStream = ByteArrayOutputStream()

		var i = 0
		while (i < sampleRate * duration) {
			val angle = (2.0 * Math.PI * i * frequency) / sampleRate
			val sample = sin(angle)

			// Apply master volume to the sample
			val byteSample = (sample * 127 * masterVolume).toInt().toByte()
			byteArrayOutputStream.write(byteSample.toInt())
			i++
		}

		val audioData: ByteArray = byteArrayOutputStream.toByteArray()
		val audioFormat = AudioFormat(sampleRate.toFloat(), 8, 1, true, false)
		val clip: Clip = AudioSystem.getClip()

		clip.open(audioFormat, audioData, 0, audioData.size)

		// Attempt to control volume using FloatControl (if supported)
		val gainControl = clip.getControl(FloatControl.Type.MASTER_GAIN) as? FloatControl
		gainControl?.let {
			val range = gainControl.minimum..gainControl.maximum
			val gain = range.start + (masterVolume * (range.endInclusive - range.start))
			gainControl.value = gain
		}

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