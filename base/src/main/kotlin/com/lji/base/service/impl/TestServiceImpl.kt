package com.lji.base.service.impl

import com.lji.base.exception.ApiRuntimeException
import com.lji.base.model.dto.TestInDto
import com.lji.base.model.dto.TestInfoOutDto
import com.lji.base.model.response.ApiResult
import com.lji.base.model.schema.Test
import com.lji.base.repository.TestRepository
import com.lji.base.service.TestService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

/*
* @name: TestServiceImpl.kt
* @date : 2023-12-14
* @author : 이재익
*/

@Service
class TestServiceImpl(private val testRepository: TestRepository) : TestService {

    @Transactional
    override fun insertTest(testInDto: TestInDto): String {

        if (!StringUtils.hasText(testInDto.testName)) {
            throw ApiRuntimeException(ApiResult.INVAILD_AGUMENTS)
        }

        val test = Test.create(testInDto)
        testRepository.save(test)

        return "success"
    }

    @Transactional(readOnly = true)
    override fun getTest(testId: Long): TestInfoOutDto {

        val test = testRepository.findById(testId).orElseThrow { ApiRuntimeException(ApiResult.NO_DATA) }

        return TestInfoOutDto(test.testId, test.testName)
    }
}