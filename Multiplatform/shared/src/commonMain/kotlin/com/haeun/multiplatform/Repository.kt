package com.haeun.multiplatform

class Repository {
    private val githubDataSource = GithubDataSource()

    private val stringDataSource = StringDataSource()

    suspend fun getGithubUsers(q: String): GithubUsersModel =
        githubDataSource.getGithubUsers(q = q)

    fun getSavedString(): String =
        stringDataSource.getSavedString()

    fun saveString(string: String) =
        stringDataSource.saveString(string)
}