package com.yuk.cspcli.domain

data class ArchiveDTO(val id: Int,
                      val name: String)

data class ArchiveDetailDTO(val archive: ArchiveDTO,
                            val storageList: List<StorageDTO>)

data class ArchiveRequestDTO(val name: String)