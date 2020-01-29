package com.yuk.cspcli.shell

import com.yuk.cspcli.api.ArchiveApi
import com.yuk.cspcli.domain.ArchiveDTO
import com.yuk.cspcli.shell.helper.InputHelper
import com.yuk.cspcli.shell.helper.ShellHelper
import com.yuk.cspcli.shell.helper.TableMaker
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("archive")
class ArchiveComponent(private val archiveApi: ArchiveApi,
                       private val tableMaker: TableMaker,
                       private val inputHelper: InputHelper,
                       private val shellHelper: ShellHelper) {
    @ShellMethod("show all archive", group = "archive", key = ["archive list"])
    @ShellMethodAvailability("connectCheck")
    fun showArchiveList() {
        val archiveList = archiveApi.showArchiveList()
        tableMaker.printTable(archiveList, ArchiveDTO::class)
    }

    @ShellMethod("add new archive", group = "archive", key = ["archive add"])
    @ShellMethodAvailability("connectCheck")
    fun addArchive() {
        shellHelper.printInfo("please input archive name")
        val name = inputHelper.prompt("name")
        if (name.isBlank()) {
            shellHelper.printInfo("archive name must not black or null")
            return
        }
        archiveApi.addArchive(name)
        shellHelper.printInfo("archive $name created")
    }

    @ShellMethod("delete archive", group = "archive", key = ["archive delete"])
    @ShellMethodAvailability("connectCheck")
    fun deleteArchive() {
        shellHelper.printInfo("please input your archive Id")
        val id = inputHelper.prompt("id").toIntOrNull()
                ?: run {
                    shellHelper.printInfo("archive name must be integer Number")
                    return
                }
        archiveApi.deleteArchive(id)
        shellHelper.printInfo("archive deleted")
    }
}