package com.yuk.cspcli.shell.helper

import org.jline.reader.LineReader


class InputHelper(private val lineReader: LineReader) {
    fun prompt(prompt: String): String {
        val answer = lineReader.readLine("$prompt: ")
        if (answer.isBlank())
            throw IllegalArgumentException("input $prompt is blank or null. it's not allowed")
        return answer
    }

    fun promptInt(prompt: String): Int {
        val answer = prompt(prompt).toIntOrNull() ?: run {
            throw IllegalArgumentException("input $prompt is not Integer. only Integer allowed")
        }
        if (answer < 1) throw IllegalArgumentException("input $answer must be positive")
        return answer
    }
}