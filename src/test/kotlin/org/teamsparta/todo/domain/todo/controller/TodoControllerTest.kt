package org.teamsparta.todo.domain.todo.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.teamsparta.todo.domain.todo.dto.TodosResponse
import org.teamsparta.todo.domain.todo.service.TodoService
import org.teamsparta.todo.domain.user.model.User
import org.teamsparta.todo.infra.security.JwtTokenPlugin
import java.time.LocalDateTime

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockKExtension::class)
class TodoControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtTokenPlugin: JwtTokenPlugin
): DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val todoService = mockk<TodoService>()

    describe("GET /todos/{id}") {
        context("존재하는 ID를 요청할 때") {
            it("200 status code를 응답한다.") {
                val todoId = 6L

                every { todoService.getTodoById(any()) } returns TodosResponse(
                    id = todoId,
                    title = "test-title1",
                    content = "test-content1",
                    authorName = "test-name1",
                    date = LocalDateTime.now(),
                    status = false,
                    comments = mutableListOf()
                )

                val jwtToken = jwtTokenPlugin.generateAccessToken(
                    user = User(
                        email = "test@test.com",
                        password = "testPassword",
                        nickname = "testNickname"
                    )
                )
                val result = mockMvc.perform(
                    get("/todos/$todoId")
                        .header("Autorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                result.response.status shouldBe 200

                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    TodosResponse::class.java
                )

                responseDto.id shouldBe todoId
            }
        }
    }
})