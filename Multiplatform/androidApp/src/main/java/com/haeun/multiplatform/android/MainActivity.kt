package com.haeun.multiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeun.multiplatform.GithubUserModel
import com.haeun.multiplatform.GithubUsersModel
import com.haeun.multiplatform.Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SampleView()
                }
            }
        }
    }
}

@Composable
fun SampleView() {
    val repository = Repository()
    var githubUsers: GithubUsersModel? by remember { mutableStateOf(null) }
    var savedString by remember { mutableStateOf(repository.getSavedString()) }
    LaunchedEffect(Unit) {
        githubUsers = repository.getGithubUsers(q = "chlgkdms")
    }
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        item {
            EditSavedString(savedString = savedString) {
                repository.saveString(it)
                savedString = repository.getSavedString()
            }
        }
        items(githubUsers?.items?.size ?: 0) { index ->
            githubUsers?.items?.get(index)?.let {
                GithubRepositoryItemView(it)
            }
        }
    }
}


@Composable
fun EditSavedString(savedString: String, onSaveNewString: (String) -> Unit) {
    var newString by remember { mutableStateOf(savedString) }
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            value = newString,
            onValueChange = { newString = it }
        )
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray),
            onClick = { onSaveNewString(newString) }
        ) {
            Text(text = "저장", color = Color.DarkGray, fontSize = 16.sp)
        }
    }
}

@Composable
fun GithubRepositoryItemView(item: GithubUserModel) {
    Column(Modifier.padding(8.dp)) {
        Text(text = item.fullName, color = Color.DarkGray, fontSize = 16.sp)
        item.description?.let { Text(text = it, color = Color.Gray, fontSize = 12.sp) }
    }
}