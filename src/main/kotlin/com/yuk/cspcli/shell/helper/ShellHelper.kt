package com.yuk.cspcli.shell.helper

import org.jline.terminal.Terminal
import org.jline.utils.AttributedStringBuilder
import org.jline.utils.AttributedStyle

class ShellHelper(private val terminal: Terminal) {
    private fun getColored(message: String, color: PromptColor) =
            AttributedStringBuilder().append(message, AttributedStyle.DEFAULT.foreground(color.value)).toAnsi()

    private fun getInfoMessage(message: String) =
            getColored(message, PromptColor.BLUE)

    private fun getSuccessMessage(message: String) =
            getColored(message, PromptColor.GREEN)

    private fun getWarningMessage(message: String) =
            getColored(message, PromptColor.YELLOW)

    private fun getErrorMessage(message: String) =
            getColored(message, PromptColor.RED)

    fun printSuccess(message: String) {
        print(message, PromptColor.BLUE)
    }

    fun printInfo(message: String) {
        print(message, PromptColor.GREEN)
    }

    fun printWarning(message: String) {
        print(message, PromptColor.YELLOW)
    }

    fun printError(message: String) {
        print(message, PromptColor.RED)
    }

    fun print(message: String, color: PromptColor = PromptColor.WHITE) {
        val toPrint = getColored(message, color)
        terminal.writer().println(toPrint)
        terminal.flush()
    }
}