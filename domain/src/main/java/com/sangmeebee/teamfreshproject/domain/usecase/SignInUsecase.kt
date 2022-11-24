package com.sangmeebee.teamfreshproject.domain.usecase

import com.sangmeebee.teamfreshproject.domain.model.SignInRequest
import com.sangmeebee.teamfreshproject.domain.model.Token
import com.sangmeebee.teamfreshproject.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUsecase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(signInRequest: SignInRequest): Result<Token> =
        signInRepository.signIn(signInRequest)
}
