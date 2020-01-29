package com.yuk.cspcli.shell

import com.yuk.cspcli.api.StorageApi
import com.yuk.cspcli.domain.StorageDTO
import com.yuk.cspcli.domain.StorageRequestDto
import com.yuk.cspcli.domain.StorageType
import com.yuk.cspcli.shell.helper.InputHelper
import com.yuk.cspcli.shell.helper.ShellHelper
import com.yuk.cspcli.shell.helper.TableMaker
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("storage")
class StorageComponent(private val storageApi: StorageApi,
                       private val tableMaker: TableMaker,
                       private val inputHelper: InputHelper,
                       private val shellHelper: ShellHelper) {
    @ShellMethod("show all storage", group = "storage", key = ["storage list"])
    @ShellMethodAvailability("connectCheck")
    fun showStorageList() {
        val storageList = storageApi.showStorageList()
        tableMaker.printTable(storageList, StorageDTO::class)
    }

    @ShellMethod("add new storage", group = "storage", key = ["storage add"])
    @ShellMethodAvailability("connectCheck")
    fun addStorageList() {
        shellHelper.printInfo("add new storage")
        val name = inputHelper.prompt("please input storage Name")
        if (name.isBlank()) {
            shellHelper.printInfo("storage name can't be blank")
            return
        }

        val path = inputHelper.prompt("please input storage path")
        if (path.isBlank()) {
            shellHelper.printInfo("storage path can't be blank")
            return
        }

        val type = inputHelper.prompt("storage Type => 1: disk 2: S3").toIntOrNull()
                ?: run {
                    shellHelper.printInfo("input is not integer number")
                    return
                }
        val requestDto = when (type) {
            1 -> StorageRequestDto(name, path, StorageType.DISK)
            2 -> StorageRequestDto(name,path,StorageType.S3)
            else -> {
                shellHelper.printInfo("storage type is wrong")
                return
            }
        }

        storageApi.addStorage(requestDto)
    }

    @ShellMethod("delete storage", group = "storage", key = ["storage delete"])
    @ShellMethodAvailability("connectCheck")
    fun deleteStorageList() {
        shellHelper.printInfo("delete storage for existed")
        val id = inputHelper.prompt("storage Id").toIntOrNull() ?: run {
            shellHelper.printInfo("input is not integer number")
            return
        }

        storageApi.deleteStorage(id)
    }
}