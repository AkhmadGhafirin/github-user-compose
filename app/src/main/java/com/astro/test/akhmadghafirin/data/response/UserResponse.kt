package com.astro.test.akhmadghafirin.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("login") val login: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Long?,
)

data class SearchResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("incomplete_results") val incompleteResults: Boolean?,
    @SerializedName("items") val items: List<UserResponse>?
)