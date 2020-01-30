package com.yuk.cspcli.api

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.client.methods.*
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.impl.client.HttpClients
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.HttpRetryException
import java.net.SocketException


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

    fun post(subUrl: String, headers: Map<String, String> = mapOf()) {
        val post = HttpPost(makeUrl(subUrl))
        basicSend(post, headers)
    }

    fun <T> deleteAndGetData(subUrl: String, returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val deleteRequest = HttpDelete(makeUrl(subUrl))
        return basicDataGet(deleteRequest, returnType, headers)
    }

    fun delete(subUrl: String, headers: Map<String, String> = mapOf()) {
        val deleteRequest = HttpDelete(makeUrl(subUrl))
        return basicSend(deleteRequest, headers)
    }

    fun <T> get(subUrl: String, returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val getRequest = HttpGet(makeUrl(subUrl))
        return basicDataGet(getRequest, returnType, headers)
    }

    fun <T> head(subUrl: String, returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val headRequest = HttpHead(makeUrl(subUrl))
        return basicDataGet(headRequest, returnType, headers)
    }

    @Throws(IOException::class)
    private fun <T> basicDataSendingAndGet(httpRequest: HttpEntityEnclosingRequestBase, sendingData: Any,
                                           returnType: Class<T>, headers: Map<String, String> = mapOf()): T {
        val requestJson = objectMapper.writeValueAsString(sendingData)
        httpRequest.entity = StringEntity(requestJson, ContentType.APPLICATION_JSON)
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        val responseJson = networkHandle(httpRequest, BasicResponseHandler())
        return jsonParse(responseJson, returnType)
    }

    @Throws(IOException::class)
    private fun <T> basicDataGet(httpRequest: HttpRequestBase, returnType: Class<T>, headers: Map<String, String>): T {
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        val responseJson = networkHandle(httpRequest, BasicResponseHandler())
        return jsonParse(responseJson, returnType)
    }

    @Throws(IOException::class)
    private fun basicDataSending(httpRequest: HttpEntityEnclosingRequestBase, sendingData: Any,
                                 headers: Map<String, String> = mapOf()) {
        val json = objectMapper.writeValueAsString(sendingData)
        httpRequest.entity = StringEntity(json, ContentType.APPLICATION_JSON)
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        networkHandle(httpRequest, BasicResponseHandler())
    }

    @Throws(IOException::class)
    private fun basicSend(httpRequest: HttpRequestBase, headers: Map<String, String>) {
        headers.forEach { (t, u) -> httpRequest.addHeader(t, u) }
        networkHandle(httpRequest, BasicResponseHandler())
    }

    private fun makeUrl(subUrl: String): String {
        return if (port == 0)
            "$ip$subUrl"
        else
            "$ip:$port$subUrl"
    }

    private fun networkHandle(httpRequest: HttpRequestBase, basicResponseHandler: BasicResponseHandler): String {
        try {
            return client.execute(httpRequest, basicResponseHandler)
        } catch (e: SocketException) {
            throw IllegalStateException(e.message)
        } catch (e: HttpRetryException) {
            throw IllegalStateException(e.message)
        }
    }

    private fun <T> jsonParse(responseJson: String, returnType: Class<T>): T {
        try {
            if (returnType.name == "java.lang.String")
                return responseJson as T
            return objectMapper.readValue(responseJson, returnType)
        } catch (e: JsonProcessingException) {
            throw IllegalStateException(e.message)
        } catch (e: JsonMappingException) {
            throw IllegalStateException(e.message)
        }
    }
}