package com.yuk.cspcli.shell

import com.yuk.cspcli.api.CommonApi
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("storage")
class StorageComponent(private val apiApi: CommonApi) {
    @ShellMethod("show all storage",group = "storage",key = ["storage"])
    @ShellMethodAvailability("connectCheck")
    fun showStorageList(){

    }
}