package dev.bitbakery.boilerplate.auth

import dev.bitbakery.boilerplate.inject.createTestComponent
import dev.bitbakery.boilerplate.network.ApiError
import dev.bitbakery.boilerplate.user.data.UserNetworkModel
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.network.sockets.SocketTimeoutException
import kotlinx.coroutines.runBlocking

class LoginManagerTest :
    FunSpec({
        test("Should return HttpError when API responds with HTTP error") {
            val engine =
                MockEngine {
                    respondError(HttpStatusCode.Unauthorized)
                }
            val component = createTestComponent(engine)
            val loginManager = component.loginManager
            runBlocking {
                loginManager
                    .login("username", "password")
                    .shouldBeLeft()
                    .shouldBeTypeOf<ApiError.HttpError>()
            }
        }
        test("Should return NetworkError when network is not available") {
            val engine =
                MockEngine {
                    throw SocketTimeoutException()
                }
            val component = createTestComponent(engine)
            val loginManager = component.loginManager
            runBlocking {
                loginManager
                    .login("username", "password")
                    .shouldBeLeft()
                    .shouldBeTypeOf<ApiError.NetworkError>()
            }
        }
        test("Should return SerializationError when response body can't be parsed") {
            val engine =
                MockEngine {
                    respond(
                        content = "",
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val component = createTestComponent(engine)
            val loginManager = component.loginManager
            runBlocking {
                loginManager
                    .login("username", "password")
                    .shouldBeLeft()
                    .shouldBeTypeOf<ApiError.SerializationError>()
            }
        }
        test("Should return User object when request is successful") {
            val engine =
                MockEngine {
                    respond(
                        content =
                            """
                            {
                                "user": {
                                    "id": "1",
                                    "token": "aabbccdd",
                                    "username": "johndoe"
                                },
                                "userDefaultLibraryId": "123"
                            }
                            """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json"),
                    )
                }
            val component = createTestComponent(engine)
            val loginManager = component.loginManager
            val tokenStorage = component.tokenStorage
            runBlocking {
                loginManager
                    .login("username", "password")
                    .shouldBeRight()
                    .shouldBeTypeOf<UserNetworkModel>()

                tokenStorage
                    .getToken()
                    .shouldNotBeNull()
                    .shouldBeEqual("aabbccdd")
            }
        }
    })
