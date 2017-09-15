package com.eric.main

import com.eric.main.Generator
import java.io.File
import java.io.FileNotFoundException

fun main(args: Array<String>) {

	val output = if (args.size > 0) {
		val input = if (args[0].isEmpty()) "" else args[0]
		val f = File(input)
		if (f.exists())
			Generator.gen(f)
		 else
			throw FileNotFoundException("File: $input not found.")
	} else {
		throw IllegalArgumentException("args.size = $args.size. ")
	}
	println(output)

}
