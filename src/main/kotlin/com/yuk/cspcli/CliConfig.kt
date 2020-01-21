package com.yuk.cspcli

import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.shell.jline.PromptProvider

@Configuration
class CliConfig {
    @Bean
    fun myPromptProvider(): PromptProvider? {
        return PromptProvider {
            AttributedString("CSP:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE))
        }
    }
}