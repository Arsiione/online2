package com.example.online.backend

import com.example.online.backend.database.DatabaseFactory
import com.example.online.data.repository.UserRepositoryImpl
import com.example.online.backend.routes.authRoutes
import com.example.online.backend.service.AuthService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import kotlinx.serialization.json.Json

object Server {
    private var server: ApplicationEngine? = null
    private lateinit var userRepository: UserRepositoryImpl
    private lateinit var authService: AuthService

    private const val SECRET = "your-secret-key" // В реальном приложении должен храниться в защищенном месте
    private const val ISSUER = "online-courses"
    private const val AUDIENCE = "users"

    fun start() {
        // Инициализация базы данных
        DatabaseFactory.init()
        
        // Инициализация репозиториев и сервисов
        userRepository = UserRepositoryImpl()
        authService = AuthService(userRepository)

        server = embeddedServer(Netty, port = 8080) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Authentication) {
                jwt {
                    verifier(
                        JWT.require(Algorithm.HMAC256(SECRET))
                            .withAudience(AUDIENCE)
                            .withIssuer(ISSUER)
                            .build()
                    )
                    validate { credential ->
                        if (credential.payload.audience.contains(AUDIENCE)) {
                            JWTPrincipal(credential.payload)
                        } else null
                    }
                }
            }
            
            configureRouting()
        }.start(wait = false)
    }

    fun stop() {
        server?.stop(1000, 2000)
        server = null
    }

    private fun Application.configureRouting() {
        routing {
            authRoutes(authService)
        }
    }
} 