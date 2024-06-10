package org.teamsparta.todo.infra.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthArgumentResolver(
    private val jwtTokenPlugin: JwtTokenPlugin
): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(RequestUser::class.java) && parameter.parameterType.isAssignableFrom(
            AuthUser::class.java
        )
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): AuthUser {
        val request: HttpServletRequest =
            webRequest.getNativeRequest(HttpServletRequest::class.java) ?: throw IllegalArgumentException()
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)?.replace("Bearer ", "")?.trim() ?: ""

        if (!jwtTokenPlugin.validateToken(token)) throw IllegalArgumentException("Invalid Token")
        if (jwtTokenPlugin.getSubject(token) != TokenType.ACCESS_TOKEN.name) throw IllegalArgumentException("ACCESS_TOKEN 아닙니다.")

        return AuthUser(id = jwtTokenPlugin.getUserId(token), token = token)
    }
}