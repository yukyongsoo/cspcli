package com.yuk.cspcli.api

import com.yuk.cspcli.domain.StorageDTO
import com.yuk.cspcli.domain.StorageRequestDto
import org.springframework.stereotype.Component

@Component
class StorageApi(private val errorComponent: ErrorComponent,
                 private val httpComponent: HttpComponent) {

    fun showStorageList() =
            httpComponent.get("/storage",Array<StorageDTO>::class.java).toList()

    fun addStorage(requestDto: StorageRequestDto) {
        httpComponent.postDataSending("/storage",requestDto)
    }

    fun deleteStorage(id: Int) {
        httpComponent.delete("/storage/$id")
    }
}