package com.yuk.cspcli.shell

import org.springframework.shell.Availability
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("list")
class ListComponent {
    @ShellMethod("show all storage",group = "list",key = ["storage"])
    @ShellMethodAvailability("connectCheck")
    fun showStorageList(){

    }

    @ShellMethod("show all archive",group = "list",key = ["archive"])
    @ShellMethodAvailability("connectCheck")
    fun showArchiveList(){

    }
}