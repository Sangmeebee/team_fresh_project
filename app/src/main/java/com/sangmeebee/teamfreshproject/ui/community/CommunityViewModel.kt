package com.sangmeebee.teamfreshproject.ui.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sangmeebee.teamfreshproject.domain.usecase.GetBoardUsecase
import com.sangmeebee.teamfreshproject.model.BoardModel
import com.sangmeebee.teamfreshproject.model.BoardRequestModel
import com.sangmeebee.teamfreshproject.model.mapper.toDomain
import com.sangmeebee.teamfreshproject.model.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getBoardUsecase: GetBoardUsecase,
) : ViewModel() {

    val subject = MutableSharedFlow<BoardRequestModel>()

    @OptIn(ExperimentalCoroutinesApi::class)
    val boards: Flow<PagingData<BoardModel>> =
        subject.flatMapLatest { boardRequest ->
            getBoardUsecase(boardRequest.toDomain()).map { pagingData ->
                pagingData.map { board -> board.toPresentation() }
            }
        }.cachedIn(viewModelScope)
}
