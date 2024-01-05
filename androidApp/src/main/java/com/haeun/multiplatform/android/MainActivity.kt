package com.haeun.multiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.multiplatform.android.R
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
                    color = MaterialTheme.colors.background,
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
    var newString by remember { mutableStateOf(savedString) }

    LaunchedEffect(Unit) {
        repository.getGithubUsers(newString)
    }
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        item {
            EditSavedString(
                newString = newString,
                onSaveNewString = {
                    repository.saveString(it)
                    savedString = repository.getSavedString()
                },
                onClick = { },
            )
        }
        items(githubUsers?.items?.size ?: 0) { index ->
            githubUsers?.items?.get(index)?.let {
                GithubRepositoryItemView(it)
            }
        }
    }
}


@Composable
fun EditSavedString(
    newString: String,
    onSaveNewString: (String) -> Unit,
    onClick: (String) -> Unit,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier.height(56.dp),
                value = newString,
                onValueChange = { newString },
            )
            Image(
                modifier = Modifier.clickable { onClick(newString) },
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "검색",
            )
        }
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray),
            onClick = { onSaveNewString(newString) },
        ) {
            Text(
                text = "저장",
                color = Color.DarkGray,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun GithubRepositoryItemView(item: GithubUserModel) {
    Row(
        modifier = Modifier.padding(8.dp),
    ) {
        AsyncImage(
            model = item.avatarUrl,
            contentDescription = "유저 프로필",
        )
        Text(
            text = item.fullName,
            color = Color.DarkGray,
            fontSize = 16.sp,
        )
    }
}