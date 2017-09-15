import com.eric.main.Generator
import java.io.File

fun main(args: Array<String>) {
	val const = """
                 [{
                   "name":"hello",
                   "name2":true
                 }, {
                   "name":"hello",
                   "name2":"hello2"
                 }, {
                   "name":"hello",
                   "name2":"hello3"
                 }]
                """
	val output = if (args.size > 0) {
		val input = if (args[0].isEmpty()) "" else args[0]
		val f = File(input)
		if (f.exists())
			Generator.gen(const)
		else {
			Generator.gen(f)
		}
	} else {
		Generator.gen(const)
	}
	println(output)

}
