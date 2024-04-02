package com.astro.test.akhmadghafirin.data.repository

import com.astro.test.akhmadghafirin.data.model.User
import com.astro.test.akhmadghafirin.data.response.Result

interface Repository {
    suspend fun searchUsers(query: String): Result<List<User>>
    suspend fun user(username: String): Result<User>
}