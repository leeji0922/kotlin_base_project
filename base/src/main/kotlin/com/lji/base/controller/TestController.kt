package com.lji.base.controller

import com.lji.base.model.dto.TestInDto
import com.lji.base.model.dto.TestInfoOutDto
import com.lji.base.model.response.ApiResponse
import com.lji.base.model.response.ApiResult
import com.lji.base.service.TestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/*
* @name: TestController.kt
* @date : 2023-12-14
* @author : 이재익
*/

@RestController
class TestController(private val testService: TestService) {

    @PostMapping("/test")
    fun insertTest(@RequestBody testInDto: TestInDto) : ResponseEntity<ApiResponse> {
        val result = testService.insertTest(testInDto)

        return try {
            ResponseEntity.ok(ApiResponse.Success(result))
        } catch (e: Exception) {
            ResponseEntity.ok(ApiResponse.Error(ApiResult.SERVER_ERROR))
        }
    }

    @GetMapping("/test")
    fun getTestInfo(testId: Long) : ResponseEntity<ApiResponse> {
        val result = testService.getTest(testId)
        return try {
            ResponseEntity.ok(ApiResponse.Success(result))
        } catch (e: Exception) {
            ResponseEntity.ok(ApiResponse.Error(ApiResult.SERVER_ERROR))
        }
    }
}