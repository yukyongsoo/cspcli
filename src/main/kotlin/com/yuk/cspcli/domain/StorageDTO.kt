package com.yuk.cspcli.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class StorageDTO(val id: Int,
                      val name: String,
                      val path: String,
                      val type : StorageType,
                      val usable: Boolean)

data class StorageRequestDto(
        val name: String,
        val path: String,
        val type : StorageType
)

enum class StorageType(val type : Int) {
    DISK(1),
    S3(2)
}