package com.lji.base.aop

import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.antlr.v4.runtime.IntStream
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.CodeSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

/*
* @name: ControllerLoggingAdvice.kt
* @date : 2023-12-15
* @author : 이재익
*/

@Component
@Aspect
class ControllerLoggingAdvice {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Before("execution(* com.*.*.controller.*.*(..))")
    fun beforeExecution(joinPoint: JoinPoint) {
        var id = Thread.currentThread().id
        var builder = StringBuilder()
            .append("\n[").append(id).append("] ======================================================================================>")
            .append("\n[").append(id).append("]>>> Target:: ").append(joinPoint.target.javaClass.name)

        var request: HttpServletRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        var headerNames = request.headerNames
        while (headerNames.hasMoreElements()) {
            var headerName = headerNames.nextElement()
            var headerValue = request.getHeader(headerName)
            builder.append("\n[").append(id).append("]>>> Headers[").append(headerName).append("]:: ").append(headerValue)
        }
        var codeSignature = (joinPoint.signature as CodeSignature)
        var parameterNames = codeSignature.parameterNames
        var parameterValue = joinPoint.args
        var bound = parameterNames.size
        java.util.stream.IntStream.range(0, bound).mapToObj{ i -> "\n[" + id + "]>>> Params[" + parameterNames[i] + "]:: " +(if ((Optional.ofNullable<Any?>(parameterValue[i]).isPresent))parameterValue[i].toString() else null)}
            .forEach(builder::append)

        builder.append("\n[").append(id).append("] ======================================================================================>")

        log.info(builder.toString())
    }
}