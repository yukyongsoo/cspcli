package com.yuk.cspcli.api

import com.yuk.cspcli.LoginComponent
import org.springframework.stereotype.Component

@Component
class CommonApi(private val loginComponent: LoginComponent,
                private val httpComponent: HttpComponent) {

    fun login(ip: String, port: Int, id: String, password: String) {
        httpComponent.setUrl(ip,port)
        loginComponent.login(ip,port,id,password)
    }
}