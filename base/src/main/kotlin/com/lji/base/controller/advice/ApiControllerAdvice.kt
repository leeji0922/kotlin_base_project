package com.lji.base.controller.advice

import com.lji.base.exception.ApiRuntimeException
import com.lji.base.model.response.ApiResponse
import com.lji.base.model.response.ApiResult
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
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
    fun handleException(e: Exception, response: HttpServletResponse): ResponseEntity<ApiResponse.Error> {
        e.printStackTrace()
        log.error("{} : {}",LocalDateTime.now(), e.printStackTrace().toString())

        return logicalErrorResponse(ApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR, response)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException, response: HttpServletResponse) : ResponseEntity<ApiResponse.Error> {
        e.printStackTrace()
        log.error("{} : {}",LocalDateTime.now(), e.printStackTrace().toString())

        return logicalErrorResponse(ApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR, response)
    }
    
    
    private fun logicalErrorResponse(apiResult: ApiResult, e: Exception, status: HttpStatus, response: HttpServletResponse) : ResponseEntity<ApiResponse.Error> {
        var message = getMessage(e)
        response.setHeader("errorMessageCode", apiResult.resultCode.toString())
        response.setHeader("errorMessage", apiResult.message)

        val errorResponse = ApiResponse.Error(apiResult)
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).header("errorMessage", apiResult.message).body(errorResponse)
    }

    private fun getMessage(e: Exception) : String {
        var message: String

        if (ObjectUtils.isEmpty(e)) {
            message = "unknown error occurred"
        } else {
            message = e.toString()
        }

        return message
    }

}