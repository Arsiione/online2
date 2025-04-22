package com.example.online.backend.routes

import com.example.online.backend.service.AuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

fun Route.authRoutes(authService: AuthService) {
    route("/auth") {
        post("/register") {
            try {
                val request = call.receive<RegisterRequest>()
                
                // Базовая валидация
                when {
                    request.email.isBlank() -> {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Email cannot be empty"))
                        return@post
                    }
                    request.username.isBlank() -> {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Username cannot be empty"))
                        return@post
                    }
                    request.password.isBlank() -> {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Password cannot be empty"))
                        return@post
                    }
                }
                
                val response = authService.register(request.username, request.email, request.password)
                call.respond(HttpStatusCode.Created, response)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "An unexpected error occurred"))
            }
        }

        post("/login") {
            try {
                val request = call.receive<LoginRequest>()
                
                // Базовая валидация
                when {
                    request.email.isBlank() -> {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Email cannot be empty"))
                        return@post
                    }
                    request.password.isBlank() -> {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Password cannot be empty"))
                        return@post
                    }
                }
                
                val response = authService.login(request.email, request.password)
                if (response != null) {
                    call.respond(HttpStatusCode.OK, response)
                } else {
                    call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "Invalid email or password"))
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "An unexpected error occurred"))
            }
        }
    }
} 