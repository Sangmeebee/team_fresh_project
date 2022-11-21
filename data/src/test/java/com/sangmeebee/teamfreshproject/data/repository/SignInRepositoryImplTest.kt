package com.sangmeebee.teamfreshproject.data.repository

import com.sangmeebee.teamfreshproject.data.datasource.SignInRemoteDatasource
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
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
        val signInInfo = SignInInfo(id = "appdev", password = "Timf1234")
        coEvery { signInRemoteDatasource.signIn(signInInfo) } returns Result.success(mockk())
        // when
        signInRepository.signIn(signInInfo)
        // then
        coVerify { signInRemoteDatasource.signIn(signInInfo) }
    }
}
