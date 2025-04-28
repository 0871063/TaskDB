package com.example.testdb.screens

import android.annotation.SuppressLint
import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testdb.database.Task
import com.example.testdb.database.TaskDao
import com.example.testdb.screens.items.TaskItem
import com.example.testdb.viewModel.TaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskView (navController: NavController, taskDao: TaskDao ){

    val taskViewModel = remember { TaskViewModel(taskDao) }
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    Scaffold (
        topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.secondary
                    ),
                    title = { Text("Add Task") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }

                )
        },

        content = {
              Column (
                  modifier = Modifier
                      .fillMaxSize()
                      .padding(16.dp),
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally,
              ){

                  OutlinedTextField(
                      value = name,
                      onValueChange = { name = it },
                      label = { Text("Email") },
                      modifier = Modifier.fillMaxWidth(),
                      keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                  )

                  Spacer(modifier = Modifier.height(8.dp))

                  Button(
                      onClick = {

                          if (name.isBlank() ) {

                      Toast.makeText(context, "Name cannot be empty!", Toast.LENGTH_SHORT).show()
                          } else {
                              val id = if (taskViewModel.tasks.isNotEmpty()) {
                                  taskViewModel.tasks.last().id + 1
                              } else {
                                  0 // or whatever default starting ID you want
                              }

                              val task = Task(id , name, false)
                              taskViewModel.addTask(task)
                              navController.popBackStack()
                          }
                      },
                      modifier = Modifier.fillMaxWidth(),

                  ) {
                      Text( "Save")
                  }
              }
        }

    )
}
