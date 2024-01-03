package com.haeun.multiplatform

class Repository {
    private val githubDataSource = GithubDataSource()

    private val stringDataSource = StringDataSource()

    suspend fun getGithubUsers(userName: String) =
        githubDataSource.getGithubUsers(userName = userName)

    fun getSavedString(): String =
        stringDataSource.getSavedString()

    fun saveString(string: String) =
        stringDataSource.saveString(string)
}