package com.yuk.cspcli.shell

import com.yuk.cspcli.api.CommonApi
import org.springframework.shell.Availability
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
@ShellCommandGroup("common")
class CommonComponent(private val commonApi: CommonApi) {
    private var connected = false

    @ShellMethod("connect for server. if domain ip then use port 0", group = "common", key = ["connect"])
    fun connect(ip: String, port: Int, id: String, password: String) {
        commonApi.login(ip, port, id, password)
        connected = true
    }

    fun connectCheck() = if (connected)
        Availability.available()
    else
        Availability.unavailable("you are not connected.")
}