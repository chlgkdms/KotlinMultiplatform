package com.haeun.multiplatform

expect class StringDataSource() {
    fun getSavedString(): String

    fun saveString(string: String)
}