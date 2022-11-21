package com.sangmeebee.teamfreshproject.data.service

import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.data.model.SignInInfoEntity
import com.sangmeebee.teamfreshproject.data.model.TokenEntity
import com.sangmeebee.teamfreshproject.domain.util.ID_EXCEPTION_CODE
import com.sangmeebee.teamfreshproject.domain.util.PASSWORD_EXCEPTION_CODE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
class SignInAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var signInAPI: SignInAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        signInAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SignInAPI::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `로그인이 성공하면 결과값을 반환한다`() = runTest {
        // given
        val response = MockResponse().setResponseCode(200).setBody(File("src/test/resources/sign_in_200.json").readText())
        mockWebServer.enqueue(response)
        // when
        val actual = signInAPI.signIn(signInInfoEntity = SignInInfoEntity(id = "appdev", password = "Timf1234"))
        // then
        val expected = TokenEntity(
            success = true,
            code = 0,
            message = "성공하였습니다.",
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjgiLCJ1c2VyTG9naW5JZCI6ImFwcGRldiIsInVzZXJObSI6IuyVse2FjOyKpO2KuOqzhOyglSIsInVzZXJTZXEiOjI2OCwidXNlck5jbm0iOiLslbHthYzsiqTtirjqs4TsoJUiLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiREVGQVVMVCJ9XSwiaWF0IjoxNjY5MDM5MDQ3LCJleHAiOjkyMjMzNzIwMzY4NTQ3NzV9.qXYuNk-7KdMkOFcbWfnUT_Wo4UuzC08WOY6su4I-ysQ"
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `존재하지 않는 회원이면 로그인이 실패한다`() = runTest {
        // given
        val response = MockResponse().setBody(File("src/test/resources/sign_in_id_error.json").readText())
        val expected = ID_EXCEPTION_CODE
        mockWebServer.enqueue(response)
        // when
        val actual = signInAPI.signIn(signInInfoEntity = SignInInfoEntity(id = "noUser", password = "Timf1234"))
        // then
        assertThat(actual.code).isEqualTo(expected)
    }

    @Test
    fun `비밀번호가 틀리면 로그인이 실패한다`() = runTest {
        // given
        val response = MockResponse().setBody(File("src/test/resources/sign_in_password_error.json").readText())
        val expected = PASSWORD_EXCEPTION_CODE
        mockWebServer.enqueue(response)
        // when
        val actual = signInAPI.signIn(signInInfoEntity = SignInInfoEntity(id = "appdev", password = "WrongPw"))
        // then
        assertThat(actual.code).isEqualTo(expected)
    }
}
