package com.example.wenjiezhou_shengwang_comp304sec002_lab06

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SEMCoursesScreen(navController: NavHostController, selectedProgram: String) {

    val semCourses = getSEMCoursesForProgram(selectedProgram)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        semCourses.forEach { course ->
            TextButton(
                onClick = {
                    navController.navigate("course_description/$course")
                },
                modifier = Modifier
                    .padding(vertical = 15.dp)
            ) {
                Text(
                    text = course,
                    color = MaterialTheme.colors.primary,
                    fontSize = 18.sp
                )
            }
        }
    }
}

fun getSEMCoursesForProgram(selectedProgram: String): List<String> {
    return when (selectedProgram) {
        "Software Engineering Technology" -> listOf("Web Application Development", "Data Structures and Algorithms", "Mobile Apps Development")
        "Artificial Intelligence Software Engineering" -> listOf("Introduction to Artificial Intelligence", "Supervised Learning", "Neural Networks")
        "Computer Systems Technology" -> listOf("Introduction to Telephony", "Virtualization and Cloud Technology", "Network Security")
        else -> emptyList()
    }
}
