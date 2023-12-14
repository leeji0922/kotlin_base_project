package com.lji.base.model.response

/*
* @name: ApiResponse.kt
* @date : 2023-12-14
* @author : 이재익
*/

sealed class ApiResponse {
    data class Success<T>(
            val result: ApiResult,
            val resultCode: Int,
            val data: T,
            val message: String
    ) : ApiResponse() {
        constructor(data: T) : this(ApiResult.SUCCESS, ApiResult.SUCCESS.resultCode, data, ApiResult.SUCCESS.message)
    }

    data class Error(
        val result: ApiResult,
        val resultCode: Int,
        val data: Any?,
        val message: String
    ) : ApiResponse() {
        constructor() : this(ApiResult.SERVER_ERROR, ApiResult.SERVER_ERROR.resultCode, null, ApiResult.SERVER_ERROR.message)
        constructor(result: ApiResult) : this(result, result.resultCode, null, result.message)
    }
}
