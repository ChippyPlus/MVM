import java.awt.BorderLayout
import java.io.PrintStream
import javax.swing.JFrame
import javax.swing.JLabel

fun main() {
	setupErrorStreamFilter()
	val frame = JFrame("My Kotlin Swing App")
	frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
	frame.setSize(300, 200)
	val label = JLabel("Hello, Kotlin Swing!")
	frame.add(label, BorderLayout.CENTER)
	frame.isVisible = true

}


fun setupErrorStreamFilter() {
	val process = ProcessBuilder(
		"sh",
		"-c",
		"while read line; do if [[ ! \$line =~ IMK[A-Za-z]+ ]]; then echo \"\$line\" >&2; fi ; done"
	).start()

	// Redirect Java's stderr to the shell process's stdin
	System.setErr(PrintStream(process.outputStream))
}
