package com.lji.base.repository

import com.lji.base.model.schema.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/*
* @name: TestRepository.kt
* @date : 2023-12-14
* @author : 이재익
*/

@Repository
interface TestRepository : JpaRepository<Test, Long> {
}