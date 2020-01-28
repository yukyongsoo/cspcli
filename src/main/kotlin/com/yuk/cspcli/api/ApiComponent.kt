package com.yuk.cspcli.api

import com.yuk.cspcli.LoginComponent
import org.springframework.stereotype.Component

@Component
class ApiComponent(private val errorComponent: ErrorComponent,
                   private val loginComponent: LoginComponent) {
    private var ip = ""
    private var port = 0

    fun login(ip: String, port: Int, id: String, password: String) {
        loginComponent.login(ip,port,id,password)
        this.ip = ip
        this.port = port
    }

    private fun getLoginToken() : String {
        val token = loginComponent.token
        if(token.isBlank())
            throw IllegalStateException("token not found. check your login state")
        return token
    }

    private fun makeUrl() = "$ip:$port"
}