package com.example.testdb.screens.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testdb.database.Task
import com.example.testdb.database.TaskDao
import com.example.testdb.viewModel.TaskViewModel

@Composable
fun TaskItem (navController: NavController, task: Task, taskViewModel: TaskViewModel) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                taskViewModel.markDone(task)
            }
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text( text = task.name)

            if(task.isDOne){
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Task Selected",
                    modifier = Modifier,
                    tint = Color.Green
                )
            }
        }
    }
}