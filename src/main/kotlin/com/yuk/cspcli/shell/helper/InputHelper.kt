package com.yuk.cspcli.shell.helper

import org.jline.reader.LineReader


class InputHelper(private val lineReader: LineReader) {
    private val mask = '*'

    fun prompt(prompt: String, defaultValue: String = "", echo: Boolean = true): String {
        var answer = ""
        answer = if (echo) {
            lineReader.readLine("$prompt: ")
        } else {
            lineReader.readLine("$prompt: ", mask)
        }
        return if (answer.isBlank()) {
            defaultValue
        } else answer
    }
}