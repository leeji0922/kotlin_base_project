package com.lji.base.model.response

import lombok.Getter

/*
* @name: ApiResult.kt
* @date : 2023-12-14
* @author : 이재익
*/

@Getter
enum class ApiResult {

    // 성공
    SUCCESS(200, "성공"),

    // 서버에러
    SERVER_ERROR(-1, "server error"),

    // invaild
    INVAILD_AGUMENTS(1000, "invaild aguments"),

    // no data
    NO_DATA(2000, "no data"),


    // unknown
    UNKNOWN(0, "unknown");

    var resultCode: Int
    var message: String

    constructor(resultCode: Int, message: String) {
        this.resultCode = resultCode
        this.message = message
    }

    constructor(resultCode: Int) {
        this.resultCode = resultCode
        this.message = searchByCode(resultCode)?.message ?: UNKNOWN.message
    }

    companion object {
        private val lookup = values().associateBy(ApiResult::resultCode)

        fun searchByCode(resultCode: Int): ApiResult? {
            return lookup[resultCode] ?: UNKNOWN
        }
    }
}