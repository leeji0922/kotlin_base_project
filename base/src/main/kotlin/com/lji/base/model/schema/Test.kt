package com.lji.base.model.schema

import com.lji.base.model.dto.TestInDto
import jakarta.persistence.*

/*
* @name: Test.kt
* @date : 2023-12-14
* @author : 이재익
*/
@Entity
@Table(name = "test")
class Test(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "test_id") val testId: Long,

        testName: String
) {

    @Column(name = "test_name")
    var testName = testName
        protected set

    constructor(testName: String) : this(0, testName)

    companion object {
        fun create(testInDto: TestInDto): Test {
            return Test(testInDto.testName)
        }
    }
}

