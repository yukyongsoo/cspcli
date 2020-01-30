package com.yuk.cspcli.api

import com.yuk.cspcli.domain.ArchiveDTO
import com.yuk.cspcli.domain.ArchiveDetailDTO
import com.yuk.cspcli.domain.ArchiveRequestDTO
import org.springframework.stereotype.Component

@Component
class ArchiveApi(private val httpComponent: HttpComponent) {

    fun showArchiveList(): List<ArchiveDTO> {
        return httpComponent.get("/archive", Array<ArchiveDTO>::class.java).toList()
    }

    fun addArchive(name: String) {
        httpComponent.postDataSending("/archive", ArchiveRequestDTO(name))
    }

    fun deleteArchive(id: Int) {
        httpComponent.delete("/archive/$id")
    }

    fun attachArchiveStorage(archiveId: Int, storageId: Int) {
        httpComponent.post("/archive/$archiveId/$storageId")
    }

    fun detachArchiveStorage(archiveId: Int, storageId: Int) {
        httpComponent.delete("/archive/$archiveId/$storageId")
    }

    fun getArchive(archiveId: Int): ArchiveDetailDTO {
        return httpComponent.get("/archive/$archiveId", ArchiveDetailDTO::class.java)
    }
}