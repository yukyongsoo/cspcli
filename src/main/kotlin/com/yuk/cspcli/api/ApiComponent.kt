package com.yuk.cspcli.api

import org.springframework.stereotype.Component

@Component
class ApiComponent(private val errorComponent: ErrorComponent) {
    private var token = ""

    fun login(ip: String, port: Int, id: String, password: String) {
        token = "asdf"
    }

}