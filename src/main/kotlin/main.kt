import com.github.salomonbrys.kotson.*
import java.io.File
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import java.lang.RuntimeException


var base = '|';

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


	val jsonElem = Gson().fromJson(const, JsonElement::class.java)
	val arr = toJsonArr(jsonElem)
	val outputBuilder = StringBuilder()
	val json = arr.get(0)
	if (json is JsonObject) {
		outputBuilder.append(genHeader(json))
	}

	for (i in 1..arr.size()) {
		val elem = arr[i - 1];
		if (elem is JsonObject) {
			outputBuilder.append(genBodyRow(elem))
		}
	}

	println(outputBuilder)

}


fun toJsonArr(jsonElem: JsonElement): JsonArray {
	val arr = if (jsonElem is JsonArray) {
		jsonElem
	} else if (jsonElem is JsonObject) {
		jsonArray(jsonElem)
	} else {
		throw RuntimeException("neither json array nor json object")
	}
	return arr
}


fun genHeader(json: JsonObject): String {
	val headerStr = StringBuilder()
	val headerSeperator = StringBuilder()
	headerStr.append(base)
	headerSeperator.append(base)
	for (entry in json.entrySet()) {
		headerStr.append(entry.key).append(base)
		headerSeperator.append(":---:").append(base)
	}
	val outputStr = headerStr.append("\n").append(headerSeperator).append("\n");
	return outputStr.toString()
}

fun genBodyRow(json: JsonObject): String {
	val bodyStr = StringBuilder()
	bodyStr.append(base)
	for (entry in json.entrySet()) {
		var value = entry.value
		val str = when (value) {
			is JsonPrimitive -> value.toString().replace("\"", "")
			else -> value.toString()
		}
		bodyStr.append(str).append(base)
	}
	bodyStr.append("\n");
	return bodyStr.toString()
}
