package com.lji.base.exception

import com.lji.base.model.response.ApiResult

/*
* @name: ApiRuntimeException.kt
* @date : 2023-12-14
* @author : 이재익
*/

class ApiRuntimeException(var apiResult: ApiResult) : RuntimeException(apiResult.message)  {

    constructor() : this(ApiResult.SERVER_ERROR)
}