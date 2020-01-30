package com.yuk.cspcli

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.yuk.cspcli.shell.helper.InputHelper
import com.yuk.cspcli.shell.helper.ShellHelper
import org.jline.reader.LineReader
import org.jline.terminal.Terminal
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.shell.jline.PromptProvider


@Configuration
class CliConfig {
    @Bean
    fun getPromptProvider(): PromptProvider {
        return PromptProvider {
            AttributedString("CSP :>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE))
        }
    }

    @Bean
    fun getObjectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(KotlinModule())
        }
    }

    @Bean
    fun getShellHelper(@Lazy terminal: Terminal): ShellHelper {
        return ShellHelper(terminal)
    }

    @Bean
    fun getInputHelper(@Lazy lineReader: LineReader): InputHelper {
        return InputHelper(lineReader)
    }
}