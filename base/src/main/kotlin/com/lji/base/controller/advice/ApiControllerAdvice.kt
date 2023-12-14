package com.lji.base.controller.advice

import com.lji.base.exception.ApiRuntimeException
import com.lji.base.model.response.ApiResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

/*
* @name: ApiControllerAdvice.kt
* @date : 2023-12-14
* @author : 이재익
*/
@RestControllerAdvice
@Slf4j
class ApiControllerAdvice {

    private val log = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(ApiRuntimeException::class)
    fun handleApiRuntimeException(e: ApiRuntimeException): ResponseEntity<ApiResponse> {
        var httpHeader = HttpHeaders()

        httpHeader.contentType = MediaType.APPLICATION_JSON
        val error = ApiResponse.Error(e.apiResult)
        return ResponseEntity.ok().headers(httpHeader).body(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse> {
        e.printStackTrace()
        log.error("{} : {}",LocalDateTime.now(), e.printStackTrace().toString())

        var httpHeader = HttpHeaders()
        httpHeader.contentType = MediaType.APPLICATION_JSON

        val error = ApiResponse.Error()
        return ResponseEntity.ok().headers(httpHeader).body(error)
    }

}