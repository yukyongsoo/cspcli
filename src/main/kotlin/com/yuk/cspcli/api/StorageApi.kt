package com.yuk.cspcli.api

import com.yuk.cspcli.domain.StorageDTO
import com.yuk.cspcli.domain.StorageRequestDto
import org.springframework.stereotype.Component

@Component
class StorageApi(private val httpComponent: HttpComponent) {

    fun showStorageList(): List<StorageDTO> {
        return httpComponent.get("/storage", Array<StorageDTO>::class.java).toList()
    }

    fun addStorage(requestDto: StorageRequestDto) {
        httpComponent.postDataSending("/storage", requestDto)
    }

    fun deleteStorage(id: Int) {
        httpComponent.delete("/storage/$id")
    }
}