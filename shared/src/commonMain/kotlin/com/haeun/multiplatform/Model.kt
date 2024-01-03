package com.haeun.multiplatform

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUsersModel(
    val items: List<GithubUserModel>,
)

@Serializable
data class GithubUserModel(
    @SerialName("login") val fullName: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)