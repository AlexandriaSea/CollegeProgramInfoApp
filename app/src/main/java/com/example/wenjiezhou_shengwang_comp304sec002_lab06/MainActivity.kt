package com.example.wenjiezhou_shengwang_comp304sec002_lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.wenjiezhou_shengwang_comp304sec002_lab06.ui.theme.JetComposeTheme
import androidx.compose.runtime.Composable
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeApp()
        }
    }
}

@Composable
fun JetComposeApp() {
    JetComposeTheme {

        val navController = rememberNavController()

        NavHost(navController, startDestination = "program_selection") {

            composable("program_selection") {
                ProgramSelectionScreen(navController)
            }

            composable(
                "sem_courses/{program}",
                arguments = listOf(navArgument("program") { type = NavType.StringType })
            ) { backStackEntry ->
                val program = backStackEntry.arguments?.getString("program")
                program?.let { selectedProgram ->
                    SEMCoursesScreen(navController, selectedProgram)
                }
            }

            composable(
                "course_description/{course}",
                arguments = listOf(navArgument("course") { type = NavType.StringType })
            ) { backStackEntry ->
                val course = backStackEntry.arguments?.getString("course")
                course?.let { selectedCourse ->
                    CourseDescriptionScreen(selectedCourse)
                }
            }
        }
    }
}
