package environment.sound

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.math.sin

val noteFrequencies: Map<String, Float> = mapOf(
	"A" to 440.0f,
	"A#" to 466.16f,
	"B" to 493.88f,
	"C" to 523.25f,
	"C#" to 554.37f,
	"D" to 587.33f,
	"D#" to 622.25f,
	"E" to 659.26f,
	"F" to 698.46f,
	"F#" to 739.99f,
	"G" to 783.99f,
	"G#" to 830.61f
)

val music = "C D E F G A B C D E F G A B C D E F G A B C D E F G A B".split(" ")

const val sampleRate = 44100

fun main() {
	for (i in music) {
		println("Playing | ${i}")
		play(noteFrequencies[i]!!, 0.5f)
	}


}


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