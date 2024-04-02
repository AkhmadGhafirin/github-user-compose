package com.astro.test.akhmadghafirin.data.model.mapper

import com.astro.test.akhmadghafirin.data.model.User
import com.astro.test.akhmadghafirin.data.response.UserResponse

object Mapper {
    fun UserResponse.toModel() = User(
        id = id ?: 0,
        avatarUrl = avatarUrl.orEmpty(),
        name = name.orEmpty(),
        login = login.orEmpty()
    )

    fun emptyUser() = User(
        avatarUrl = "",
        name = "",
        login = "",
        id = 0
    )
}