package com.sangmeebee.teamfreshproject.domain.usecase

import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.repository.SignInRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SignInUsecaseTest {

    @Test
    fun `로그인을 요청한다`() = runTest {
        // given
        val signInRepository: SignInRepository = mockk()
        val signInUsecase = SignInUsecase(signInRepository)
        val signInInfo = SignInInfo(id = "appdev", password = "Timf1234")
        coEvery { signInRepository.signIn(signInInfo) } returns Result.success(mockk())
        // when
        signInUsecase(signInInfo)
        // then
        coVerify { signInRepository.signIn(signInInfo) }
    }
}
