package com.yuk.cspcli.api

import com.yuk.cspcli.domain.ArchiveDTO
import org.springframework.stereotype.Component

@Component
class ArchiveApi(private val errorComponent: ErrorComponent,
                 private val httpComponent: HttpComponent) {

    fun showArchiveList() =
        httpComponent.get("/archive",Array<ArchiveDTO>::class.java).toList()
}