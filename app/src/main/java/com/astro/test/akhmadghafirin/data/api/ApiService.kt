package com.astro.test.akhmadghafirin.data.api

import com.astro.test.akhmadghafirin.data.response.SearchResponse
import com.astro.test.akhmadghafirin.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("per_page") perPage: Int,
        @Query("q") query: String
    ): Response<SearchResponse>

    @GET("users/{username}")
    suspend fun user(
        @Path("username") username: String
    ): Response<UserResponse>
}