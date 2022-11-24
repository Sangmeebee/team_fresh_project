package com.sangmeebee.teamfreshproject.ui.community

import androidx.lifecycle.SavedStateHandle
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
import com.sangmeebee.teamfreshproject.ui.community.CommunityContentFragment.Companion.DUMMY_CONTENT
import com.sangmeebee.teamfreshproject.ui.community.CommunityContentFragment.Companion.KEY_BOARD_SUBJECT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CommunityContentViewModel @Inject constructor(
    private val getBoardUsecase: GetBoardUsecase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val request: Flow<BoardRequestModel> =
        savedStateHandle.getStateFlow(KEY_BOARD_SUBJECT, "").map { subject -> BoardRequestModel(content = DUMMY_CONTENT, subject = subject) }

    @OptIn(ExperimentalCoroutinesApi::class)
    val boards: Flow<PagingData<BoardModel>> =
        request.flatMapLatest { boardRequest ->
            getBoardUsecase(boardRequest.toDomain()).map { pagingData ->
                pagingData.map { board -> board.toPresentation() }
            }
        }.cachedIn(viewModelScope)

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    fun fetchErrorState(error: String?) {
        _errorState.value = error
    }
}
