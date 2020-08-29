package com.example.playground

fun main() {
    val test = "Hello World!!!"
    println(test)

    val channels: Map<String, String> = mapOf("Main" to "The main alarm channel")
    println(channels.entries.first())

    channels.forEach {
        println(it.key)
        println(it.value)
    }
}