package com.astro.test.akhmadghafirin.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.test.akhmadghafirin.data.model.User
import com.astro.test.akhmadghafirin.data.repository.Repository
import com.astro.test.akhmadghafirin.data.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val searchResult: MutableState<List<User>> = mutableStateOf(listOf())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val error: MutableState<String> = mutableStateOf("")

    fun searchUsers(query: String) = viewModelScope.launch {
        isLoading.value = true
        when (val result = repository.searchUsers(query)) {
            is Result.Success -> {
                isLoading.value = false
                searchResult.value = result.data
            }

            is Result.Error -> {
                isLoading.value = false
                error.value = result.exception.message.orEmpty()
            }
        }
    }
}