package com.yuk.cspcli.api

import com.yuk.cspcli.LoginComponent
import org.springframework.stereotype.Component

@Component
class ApiComponent(private val errorComponent: ErrorComponent,
                   private val loginComponent: LoginComponent,
                   private val httpComponent: HttpComponent) {

    fun login(ip: String, port: Int, id: String, password: String) {
        httpComponent.setUrl(ip,port)
        loginComponent.login(ip,port,id,password)
    }

    private fun getLoginToken() : String {
        val token = loginComponent.token
        if(token.isBlank())
            throw IllegalStateException("token not found. check your login state")
        return token
    }

}