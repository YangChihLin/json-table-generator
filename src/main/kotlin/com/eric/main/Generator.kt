package com.eric.main

import com.github.salomonbrys.kotson.jsonArray
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import java.io.FileReader
import java.io.BufferedReader

object Generator {

	var base = '|';


	fun gen(f: File): String {
		return gen(f.bufferedReader().use { it.readText() });
	}

	fun gen(input: String): String {
		val jsonElem = Gson().fromJson(input, JsonElement::class.java)
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

		return outputBuilder.toString();
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
			var value: JsonElement? = entry.value
			val str = when (value) {
				is JsonPrimitive -> value.toString().replace("\"", "")
				else -> value.toString()
			}
			bodyStr.append(str).append(base)
		}
		bodyStr.append("\n");
		return bodyStr.toString()
	}

}