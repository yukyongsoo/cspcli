package com.yuk.cspcli.shell

import com.yuk.cspcli.api.CommonApi
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent

@ShellComponent
@ShellCommandGroup("type")
class TypeComponent(private val apiApi: CommonApi) {
}