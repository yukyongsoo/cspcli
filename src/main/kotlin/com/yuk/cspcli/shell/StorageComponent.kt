package com.yuk.cspcli.shell

import com.yuk.cspcli.api.StorageApi
import com.yuk.cspcli.domain.StorageDTO
import com.yuk.cspcli.shell.helper.TableMaker
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("storage")
class StorageComponent(private val storageApi: StorageApi,
                       private val tableMaker: TableMaker) {
    @ShellMethod("show all storage",group = "storage",key = ["storage"])
    @ShellMethodAvailability("connectCheck")
    fun showStorageList(){
        val storageList = storageApi.showStorageList()
        tableMaker.printTable(storageList,StorageDTO::class)
    }
}