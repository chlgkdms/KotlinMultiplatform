package com.haeun.multiplatform

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUsersModel(
    val items: List<GithubUserModel>
)

@Serializable
data class GithubUserModel(
    @SerialName("full_name") val fullName: String,
    val description: String?,
)