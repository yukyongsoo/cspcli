package com.yuk.cspcli

import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.shell.jline.PromptProvider
import org.springframework.web.client.RestTemplate


@Configuration
class CliConfig {
    @Bean
    fun getPromptProvider(): PromptProvider? {
        return PromptProvider {
            AttributedString("CSP:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE))
        }
    }

    @Bean
    fun getRestTemplate() : RestTemplate{
        return RestTemplateBuilder().build()
    }
}