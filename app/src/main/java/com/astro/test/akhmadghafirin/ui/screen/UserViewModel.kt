package com.astro.test.akhmadghafirin.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.astro.test.akhmadghafirin.data.model.User
import com.astro.test.akhmadghafirin.data.repository.Repository
import com.astro.test.akhmadghafirin.data.response.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val searchResult: MutableState<List<User>> = mutableStateOf(listOf())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val error: MutableState<String> = mutableStateOf("")
    val isFirstGenerate: MutableState<Boolean> = mutableStateOf(true)

    fun searchUsers(query: String) = CoroutineScope(Dispatchers.IO).launch {
        val mergeResult = mutableListOf<User>()
        isLoading.value = true
        isFirstGenerate.value = false
        searchResult.value = listOf()
        when (val result = repository.searchUsers(query)) {
            is Result.Success -> {
                result.data.forEach { user ->
                    val requestUserDetail = async { repository.user(user.login) }
                    try {
                        when (val resultUserDetail = requestUserDetail.await()) {
                            is Result.Success -> {
                                mergeResult.add(resultUserDetail.data)
                            }

                            is Result.Error -> {
                                mergeResult.add(user)
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        mergeResult.add(user)
                    }
                }
                isLoading.value = false
//                searchResult.value = result.data
                searchResult.value = mergeResult
            }

            is Result.Error -> {
                isLoading.value = false
                error.value = result.exception.message.orEmpty()
            }
        }
    }
}