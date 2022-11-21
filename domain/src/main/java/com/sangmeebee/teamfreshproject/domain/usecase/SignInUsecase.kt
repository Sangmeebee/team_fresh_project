package com.sangmeebee.teamfreshproject.domain.usecase

import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token
import com.sangmeebee.teamfreshproject.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUsecase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(signInInfo: SignInInfo): Result<Token> =
        signInRepository.signIn(signInInfo)
}
