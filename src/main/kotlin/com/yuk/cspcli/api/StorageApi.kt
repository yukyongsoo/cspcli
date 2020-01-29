package com.yuk.cspcli.api

import com.yuk.cspcli.domain.StorageDTO
import org.springframework.stereotype.Component

@Component
class StorageApi(private val errorComponent: ErrorComponent,
                 private val httpComponent: HttpComponent) {

    fun showStorageList() =
            httpComponent.get("/storage",Array<StorageDTO>::class.java).toList()
}