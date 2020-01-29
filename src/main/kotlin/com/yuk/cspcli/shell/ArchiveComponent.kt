package com.yuk.cspcli.shell

import com.yuk.cspcli.api.ArchiveApi
import com.yuk.cspcli.domain.ArchiveDTO
import com.yuk.cspcli.shell.helper.TableMaker
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent
@ShellCommandGroup("archive")
class ArchiveComponent(private val archiveApi: ArchiveApi,
                       private val tableMaker: TableMaker) {
    @ShellMethod("show all archive",group = "archive",key = ["archive"])
    @ShellMethodAvailability("connectCheck")
    fun showArchiveList(){
        val archiveList = archiveApi.showArchiveList()
        tableMaker.printTable(archiveList,ArchiveDTO::class)
    }
}