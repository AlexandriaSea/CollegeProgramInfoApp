package com.example.wenjiezhou_shengwang_comp304sec002_lab06

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProgramSelectionScreen(navController: NavHostController) {

    val programs = listOf("Software Engineering Technology", "Artificial Intelligence Software Engineering", "Computer Systems Technology")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        programs.forEach { program ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val programImage = painterResource(getProgramImageResource(program))

                Image(
                    painter = programImage,
                    contentDescription = "Program Image",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = {
                        navController.navigate("sem_courses/$program")
                    }
                ) {
                    Text(
                        text = program,
                        color = Color.Black,
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}

fun getProgramImageResource(program: String): Int {
    return when (program) {
        "Software Engineering Technology" -> R.drawable.software
        "Artificial Intelligence Software Engineering" -> R.drawable.ai
        "Computer Systems Technology" -> R.drawable.computer
        else -> R.drawable.img
    }
}
