package com.yuk.cspcli.shell

import com.yuk.cspcli.api.ApiComponent
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("archive")
class ArchiveComponent(private val apiComponent: ApiComponent) {
    @ShellMethod("show all archive",group = "archive",key = ["archive"])
    @ShellMethodAvailability("connectCheck")
    fun showArchiveList(){

    }
}