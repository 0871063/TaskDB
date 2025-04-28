package com.example.testdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.testdb.database.AppDatabase
import com.example.testdb.database.TaskDao
import com.example.testdb.screens.AddTaskView
import com.example.testdb.screens.TaskListView
import com.example.testdb.ui.theme.TestDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val taskDao = db.taskDao()
        enableEdgeToEdge()
        setContent {
            TestDBTheme {
                var navController = rememberNavController()

                NavHost(navController = navController, startDestination = "task_list"){
                    composable("task_list"){

                        TaskListView(navController, taskDao = taskDao)
                    }

                    composable("add_task/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                        AddTaskView(
                            navController = navController,
                            taskDao = taskDao
                        )
                    }
                }



//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }




            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestDBTheme {
        Greeting("Android")
    }
}