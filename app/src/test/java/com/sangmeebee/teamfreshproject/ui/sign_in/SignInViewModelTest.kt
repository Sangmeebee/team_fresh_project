package com.sangmeebee.teamfreshproject.ui.sign_in

import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.domain.usecase.SignInUsecase
import com.sangmeebee.teamfreshproject.domain.util.IllegalIdException
import com.sangmeebee.teamfreshproject.model.SignInInfoModel
import com.sangmeebee.teamfreshproject.model.mapper.toDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signInUsecase: SignInUsecase

    @Before
    fun setUp() {
        signInUsecase = mockk()
        signInViewModel = SignInViewModel(signInUsecase)
    }

    @Test
    fun `로그인에 성공하면 UIState의 로그인 상태가 성공된 상태로 업데이트 된다`() = runTest {
        // given
        val signInInfoModel = SignInInfoModel(id = "appdev", password = "Timf1234")
        coEvery { signInUsecase(signInInfoModel.toDomain()) } returns Result.success(mockk())
        // when
        signInViewModel.signIn(signInInfoModel)
        advanceUntilIdle()
        // then
        val actual = signInViewModel.uiState.value
        assertThat(actual.isSignIn).isTrue()
    }

    @Test
    fun `로그인에 실패하면 UIState의 오류 상태가 업데이트 된다`() = runTest {
        // given
        val signInInfoModel = SignInInfoModel(id = "WrongId", password = "Timf1234")
        coEvery { signInUsecase(signInInfoModel.toDomain()) } returns Result.failure(IllegalIdException())
        // when
        signInViewModel.signIn(signInInfoModel)
        advanceUntilIdle()
        // then
        val actual = signInViewModel.uiState.value
        assertThat(actual.error).isInstanceOf(IllegalIdException::class.java)
    }
}
