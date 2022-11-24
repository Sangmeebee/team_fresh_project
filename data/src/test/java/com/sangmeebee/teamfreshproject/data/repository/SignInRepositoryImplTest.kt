package com.sangmeebee.teamfreshproject.data.repository

import com.sangmeebee.teamfreshproject.data.datasource.SignInRemoteDatasource
import com.sangmeebee.teamfreshproject.domain.model.SignInRequest
import com.sangmeebee.teamfreshproject.domain.repository.SignInRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SignInRepositoryImplTest {

    @Test
    fun `로그인을 요청한다`() = runTest {
        // given
        val signInRemoteDatasource: SignInRemoteDatasource = mockk()
        val signInRepository: SignInRepository = SignInRepositoryImpl(signInRemoteDatasource)
        val signInRequest = SignInRequest(id = "appdev", password = "Timf1234")
        coEvery { signInRemoteDatasource.signIn(signInRequest) } returns Result.success(mockk())
        // when
        signInRepository.signIn(signInRequest)
        // then
        coVerify { signInRemoteDatasource.signIn(signInRequest) }
    }
}
