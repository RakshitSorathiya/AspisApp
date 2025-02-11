package com.aspis.tech.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspis.tech.api.ApiState
import com.aspis.tech.api.UrlRepository
import com.aspis.tech.model.Url
import com.aspis.tech.model.UrlModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UrlViewModel(private val urlRepository: UrlRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiState.UiState<UrlModel>>(ApiState.UiState.Loading)

    val uiState: StateFlow<ApiState.UiState<UrlModel>> = _uiState

    init {
        fetchUrlData()
    }

    private fun fetchUrlData() {
        viewModelScope.launch {
            urlRepository.getUrls().catch { it ->
                _uiState.value = ApiState.UiState.Error(it.toString())
            }.collect {
                _uiState.value = ApiState.UiState.Success(it)
            }
        }
    }
}