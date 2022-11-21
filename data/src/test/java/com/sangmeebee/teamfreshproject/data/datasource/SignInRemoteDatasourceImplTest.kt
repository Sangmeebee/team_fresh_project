package com.sangmeebee.teamfreshproject.data.datasource

import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.data.model.SignInInfoEntity
import com.sangmeebee.teamfreshproject.data.model.TokenEntity
import com.sangmeebee.teamfreshproject.data.service.SignInAPI
import com.sangmeebee.teamfreshproject.domain.util.IllegalIdException
import com.sangmeebee.teamfreshproject.domain.util.IllegalPasswordException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInRemoteDatasourceImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var signInAPI: SignInAPI
    private lateinit var signInRemoteDatasource: SignInRemoteDatasource

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        signInAPI = mockk()
        signInRemoteDatasource = SignInRemoteDatasourceImpl(signInAPI = signInAPI, dispatcher = mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `회원가입을 성공하면 토큰을 반환한다`() = runTest {
        // given
        val signInInfoEntity = SignInInfoEntity(id = "appdev", password = "Timf1234")
        val tokenEntity = TokenEntity(
            success = true,
            code = 0,
            message = "성공하였습니다.",
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjgiLCJ1c2VyTG9naW5JZCI6ImFwcGRldiIsInVzZXJObSI6IuyVse2FjOyKpO2KuOqzhOyglSIsInVzZXJTZXEiOjI2OCwidXNlck5jbm0iOiLslbHthYzsiqTtirjqs4TsoJUiLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiREVGQVVMVCJ9XSwiaWF0IjoxNjY5MDM5MDQ3LCJleHAiOjkyMjMzNzIwMzY4NTQ3NzV9.qXYuNk-7KdMkOFcbWfnUT_Wo4UuzC08WOY6su4I-ysQ"
        )
        coEvery { signInAPI.signIn(signInInfoEntity) } returns tokenEntity
        // when
        val actual = signInRemoteDatasource.signIn(signInInfo = signInInfoEntity.toDomain())
        // then
        assertThat(actual.isSuccess).isTrue()
        actual.onSuccess { assertThat(it).isEqualTo(tokenEntity.toDomain()) }
    }

    @Test
    fun `아이디를 잘못 입력하면 해당 예외가 발생한다`() = runTest {
        // given
        val signInInfoEntity = SignInInfoEntity(id = "WrongUser", password = "Timf1234")
        val tokenEntity = TokenEntity(
            success = false,
            code = -1000,
            message = "존재하지 않는 회원입니다."
        )
        coEvery { signInAPI.signIn(signInInfoEntity) } returns tokenEntity
        // when
        val actual = signInRemoteDatasource.signIn(signInInfo = signInInfoEntity.toDomain())
        // then
        assertThat(actual.isFailure).isTrue()
        actual.onFailure { assertThat(it).isInstanceOf(IllegalIdException::class.java) }
    }

    @Test
    fun `비밀번호를 잘못 입력하면 해당 예외가 발생한다`() = runTest {
        // given
        val signInInfoEntity = SignInInfoEntity(id = "appdev", password = "WrongPassword")
        val tokenEntity = TokenEntity(
            success = false,
            code = -1001,
            message = "비밀번호가 정확하지 않습니다."
        )
        coEvery { signInAPI.signIn(signInInfoEntity) } returns tokenEntity
        // when
        val actual = signInRemoteDatasource.signIn(signInInfo = signInInfoEntity.toDomain())
        // then
        assertThat(actual.isFailure).isTrue()
        actual.onFailure { assertThat(it).isInstanceOf(IllegalPasswordException::class.java) }
    }
}
