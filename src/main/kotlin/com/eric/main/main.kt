import com.github.salomonbrys.kotson.*
import java.io.File
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import java.lang.RuntimeException
import com.eric.main.Generator


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

	val output = Generator.gen(const);
	println(output)

}
