package com.lji.base.service

import com.lji.base.model.dto.TestInDto
import com.lji.base.model.dto.TestInfoOutDto

/*
* @name: TestService.kt
* @date : 2023-12-14
* @author : 이재익
*/

interface TestService {

    fun insertTest(testInDto: TestInDto) : String

    fun getTest(testId: Long) : TestInfoOutDto
}