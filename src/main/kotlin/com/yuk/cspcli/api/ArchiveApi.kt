package com.yuk.cspcli.api

import com.yuk.cspcli.domain.ArchiveDTO
import com.yuk.cspcli.domain.ArchiveRequestDTO
import org.springframework.stereotype.Component

@Component
class ArchiveApi(private val errorComponent: ErrorComponent,
                 private val httpComponent: HttpComponent) {

    fun showArchiveList() =
        httpComponent.get("/archive",Array<ArchiveDTO>::class.java).toList()

    fun addArchive(name: String) {
        httpComponent.postDataSending("/archive",ArchiveRequestDTO(name))
    }

    fun deleteArchive(id: Int) {
        httpComponent.delete("/archive/$id")
    }
}