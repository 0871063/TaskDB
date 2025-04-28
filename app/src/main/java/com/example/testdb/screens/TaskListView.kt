package com.example.testdb.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testdb.database.TaskDao
import com.example.testdb.screens.items.TaskItem
import com.example.testdb.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListView (navController: NavController, taskDao: TaskDao){
    val taskViewModel = remember { TaskViewModel(taskDao) }
    val tasks = taskViewModel.tasks

    Scaffold (
        topBar = {
            Column {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.secondary
                    ),
                    title = { Text("Task List") }
                )

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                contentColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navController.navigate("add_task/null")
                }
            ){
                Text(text = "+")
            }
        },

        content = {
            innerPadding ->  8.dp
            LazyColumn (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            ){
                items(tasks){ task ->
                    TaskItem(navController = navController, task = task, taskViewModel = taskViewModel)
                }
            }
        }
    )
}