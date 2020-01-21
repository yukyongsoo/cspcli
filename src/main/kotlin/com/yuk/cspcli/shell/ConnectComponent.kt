package com.yuk.cspcli.shell

import org.springframework.shell.Availability
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
@ShellCommandGroup("connect")
class ConnectComponent {
    private var connected = false

    @ShellMethod("connect for server",group = "list",key = ["connect"])
    fun connect(ip : String, port: Int, id: String, password : String) {
        connected = true
    }

    fun connectCheck() = if(connected)
        Availability.available()
    else
        Availability.unavailable("you are not connected.")
}