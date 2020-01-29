package com.yuk.cspcli.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.client.methods.*
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Component


@Component
class HttpComponent(private val objectMapper: ObjectMapper) {
    private var ip = ""
    private var port = 0
    private val client = HttpClients.createDefault()

    fun setUrl(ip: String, port: Int) {
        this.ip = ip
        this.port = port
    }

    fun <T> postDataSendingAndGet(subUrl: String, sendingData: Any, returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val post = HttpPost(makeUrl(subUrl))
        return basicDataSendingAndGet(post, sendingData, returnType, headers)
    }

    fun postDataSending(subUrl: String, sendingData: Any, headers: Map<String, String> = mapOf()) {
        val post = HttpPost(makeUrl(subUrl))
        basicDataSending(post, sendingData, headers)
    }

    fun <T> deleteAndGetData(subUrl: String, returnType: Class<T>, headers: Map<String, String> = mapOf()) : T{
        val deleteRequest = HttpDelete(makeUrl(subUrl))
        return basicDataGet(deleteRequest, returnType, headers)
    }

    fun delete(subUrl: String,  headers: Map<String, String> = mapOf()) {
        val deleteRequest = HttpDelete(makeUrl(subUrl))
        return basicSend(deleteRequest, headers)
    }

    fun <T> get(subUrl: String, returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val getRequest = HttpGet(makeUrl(subUrl))
        return basicDataGet(getRequest, returnType, headers)
    }

    fun <T> head(subUrl: String, returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val headRequest = HttpHead(makeUrl(subUrl))
        return basicDataGet(headRequest,returnType, headers)
    }

    private fun <T> basicDataSendingAndGet(httpRequest: HttpEntityEnclosingRequestBase, sendingData: Any,
                                           returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val requestJson = objectMapper.writeValueAsString(sendingData)
        httpRequest.entity = StringEntity(requestJson, ContentType.APPLICATION_JSON)
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        val response = client.execute(httpRequest)
        val responseJson = EntityUtils.toString(response.entity)
        if(responseJson.isBlank())
            return "" as T
        return objectMapper.readValue(responseJson, returnType)
    }

    private fun basicDataSending(httpRequest: HttpEntityEnclosingRequestBase, sendingData: Any,
                                 headers: Map<String, String> = mapOf()) {
        val json = objectMapper.writeValueAsString(sendingData)
        httpRequest.entity = StringEntity(json, ContentType.APPLICATION_JSON)
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        val response = client.execute(httpRequest)
    }

    private fun <T> basicDataGet(httpRequest: HttpRequestBase, returnType: Class<T>, headers: Map<String, String>): T {
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        val response = client.execute(httpRequest)
        val responseJson = EntityUtils.toString(response.entity)
        if(responseJson.isBlank())
            return "" as T
        return objectMapper.readValue(responseJson, returnType)
    }

    private fun basicSend(httpRequest: HttpRequestBase , headers: Map<String, String>) {
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        val response = client.execute(httpRequest)
    }

    private fun makeUrl(subUrl: String) = "$ip:$port$subUrl"
}