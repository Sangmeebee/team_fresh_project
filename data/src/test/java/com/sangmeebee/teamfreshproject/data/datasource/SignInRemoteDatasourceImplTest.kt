package com.sangmeebee.teamfreshproject.data.datasource

import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.data.model.SignInRequestEntity
import com.sangmeebee.teamfreshproject.data.model.TokenResponseEntity
import com.sangmeebee.teamfreshproject.data.service.SignInAPI
import com.sangmeebee.teamfreshproject.domain.util.IllegalIdException
import com.sangmeebee.teamfreshproject.domain.util.IllegalPasswordException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class SignInRemoteDatasourceImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var signInAPI: SignInAPI
    private lateinit var signInRemoteDatasource: SignInRemoteDatasource

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        signInAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SignInAPI::class.java)
        signInRemoteDatasource = SignInRemoteDatasourceImpl(signInAPI = signInAPI, dispatcher = mainDispatcherRule.testDispatcher)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `회원가입을 성공하면 토큰을 반환한다`() = runTest {
        // given
        val signInRequestEntity = SignInRequestEntity(id = "appdev", password = "Timf1234")
        val tokenResponseEntity = TokenResponseEntity(
            success = true,
            code = 0,
            message = "성공하였습니다.",
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjgiLCJ1c2VyTG9naW5JZCI6ImFwcGRldiIsInVzZXJObSI6IuyVse2FjOyKpO2KuOqzhOyglSIsInVzZXJTZXEiOjI2OCwidXNlck5jbm0iOiLslbHthYzsiqTtirjqs4TsoJUiLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiREVGQVVMVCJ9XSwiaWF0IjoxNjY5MDM5MDQ3LCJleHAiOjkyMjMzNzIwMzY4NTQ3NzV9.qXYuNk-7KdMkOFcbWfnUT_Wo4UuzC08WOY6su4I-ysQ"
        )
        val response = MockResponse().setResponseCode(200).setBody(File("src/test/resources/sign_in_200.json").readText())
        mockWebServer.enqueue(response)
        // when
        val actual = signInRemoteDatasource.signIn(signInRequest = signInRequestEntity.toDomain())
        // then
        assertThat(actual.isSuccess).isTrue()
        actual.onSuccess { assertThat(it).isEqualTo(tokenResponseEntity.toDomain()) }
    }

    @Test
    fun `아이디를 잘못 입력하면 해당 예외가 발생한다`() = runTest {
        // given
        val signInRequestEntity = SignInRequestEntity(id = "WrongUser", password = "Timf1234")
        val response = MockResponse().setResponseCode(400).setBody(File("src/test/resources/sign_in_id_error.json").readText())
        mockWebServer.enqueue(response)
        // when
        val actual = signInRemoteDatasource.signIn(signInRequest = signInRequestEntity.toDomain())
        // then
        assertThat(actual.isFailure).isTrue()
        actual.onFailure { assertThat(it).isInstanceOf(IllegalIdException::class.java) }
    }

    @Test
    fun `비밀번호를 잘못 입력하면 해당 예외가 발생한다`() = runTest {
        // given
        val signInRequestEntity = SignInRequestEntity(id = "appdev", password = "WrongPassword")
        val response = MockResponse().setResponseCode(400).setBody(File("src/test/resources/sign_in_password_error.json").readText())
        mockWebServer.enqueue(response)
        // when
        val actual = signInRemoteDatasource.signIn(signInRequest = signInRequestEntity.toDomain())
        // then
        assertThat(actual.isFailure).isTrue()
        actual.onFailure { assertThat(it).isInstanceOf(IllegalPasswordException::class.java) }
    }
}
