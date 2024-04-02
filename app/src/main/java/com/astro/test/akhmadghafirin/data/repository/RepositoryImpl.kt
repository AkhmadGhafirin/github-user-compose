package com.astro.test.akhmadghafirin.data.repository

import com.astro.test.akhmadghafirin.data.api.ApiService
import com.astro.test.akhmadghafirin.data.model.User
import com.astro.test.akhmadghafirin.data.model.mapper.Mapper.toModel
import com.astro.test.akhmadghafirin.data.response.Result
import com.astro.test.akhmadghafirin.util.ExceptionUtil.toException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun searchUsers(query: String): Result<List<User>> {
        return try {
            val request = apiService.searchUsers(5, query)
            if (request.isSuccessful) {
                Result.Success(request.body()?.items?.map { it.toModel() } ?: listOf())
            } else {
                Result.Error(request.errorBody().toException())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}