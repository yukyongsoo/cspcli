package com.yuk.cspcli.shell

import com.yuk.cspcli.api.ArchiveApi
import com.yuk.cspcli.domain.ArchiveDTO
import com.yuk.cspcli.domain.StorageDTO
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
        shellHelper.printInfo("show all archive List")
        val archiveList = archiveApi.showArchiveList()
        tableMaker.printListTable(archiveList, ArchiveDTO::class)
    }

    @ShellMethod("get Detail of archive", group = "archive", key = ["archive"])
    @ShellMethodAvailability("connectCheck")
    fun getArchive(archiveId: Int) {
        shellHelper.printInfo("get archive Detail")
        val archiveDetail = archiveApi.getArchive(archiveId)
        tableMaker.printSingleTable(archiveDetail.archive, ArchiveDTO::class)
        shellHelper.printInfo("archive Storage List")
        tableMaker.printListTable(archiveDetail.storageList, StorageDTO::class)
    }

    @ShellMethod("add new archive", group = "archive", key = ["archive add"])
    @ShellMethodAvailability("connectCheck")
    fun addArchive() {
        shellHelper.printInfo("please input archive name")
        val name = inputHelper.prompt("name")
        archiveApi.addArchive(name)
        shellHelper.printInfo("archive $name created")
    }

    @ShellMethod("delete archive", group = "archive", key = ["archive delete"])
    @ShellMethodAvailability("connectCheck")
    fun deleteArchive() {
        shellHelper.printInfo("please input your archive Id")
        val id = inputHelper.promptInt("id")
        archiveApi.deleteArchive(id)
        shellHelper.printInfo("archive deleted")
    }


    @ShellMethod("attach storage to archive ", group = "archive", key = ["archive attach"])
    @ShellMethodAvailability("connectCheck")
    fun attachArchiveStorageList() {
        shellHelper.printInfo("attach storage to archive ")
        val archiveId = inputHelper.promptInt("archive Id")
        val storageId = inputHelper.promptInt("storage Id")
        archiveApi.attachArchiveStorage(archiveId, storageId)
        shellHelper.printInfo("archive attached")
    }

    @ShellMethod("detach storage from archive", group = "archive", key = ["archive detach"])
    @ShellMethodAvailability("connectCheck")
    fun detachArchiveStorage() {
        shellHelper.printInfo("detach storage to archive ")
        val archiveId = inputHelper.promptInt("archive Id")
        val storageId = inputHelper.promptInt("storage Id")
        archiveApi.detachArchiveStorage(archiveId, storageId)
        shellHelper.printInfo("archive detached")
    }
}