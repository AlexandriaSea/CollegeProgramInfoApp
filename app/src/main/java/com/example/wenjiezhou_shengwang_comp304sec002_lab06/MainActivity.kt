package com.example.wenjiezhou_shengwang_comp304sec002_lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeApp()
        }
    }
}

// Define navigation from program list to course description
@Composable
fun JetComposeApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "programList") {
        composable("programList") {
            ProgramList(navController)
        }
        composable("course_description/{courseName}") { backStackEntry ->
            val courseName = backStackEntry.arguments?.getString("courseName") ?: ""
            CourseDescription(courseName)
        }
    }
}

// Show each program on the screen
@Composable
fun ProgramList(navController: NavController) {

    val programs = listOf("Software Engineering Technology", "Artificial Intelligence Software Engineering", "Computer Systems Technology")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        programs.forEach { program ->
            ProgramItem(program, navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Define the layout and expand feature for each program
@Composable
fun ProgramItem(program: String, navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { isExpanded = !isExpanded },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        ) {
            Image(
                painter = painterResource(getProgramImage(program)),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = program,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (isExpanded) {

            val courses = getCoursesForProgram(program)
            Spacer(modifier = Modifier.height(8.dp))

            Column {
                courses.forEach { course ->
                    CourseItem(course, navController)
                }
            }
        }
    }
}

// Get image for each program
fun getProgramImage(program: String): Int {
    return when (program) {
        "Software Engineering Technology" -> R.drawable.software
        "Artificial Intelligence Software Engineering" -> R.drawable.ai
        "Computer Systems Technology" -> R.drawable.computer
        else -> R.drawable.img
    }
}

// Define the layout for each course and the navigation action
@Composable
fun CourseItem(course: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                navController.navigate("course_description/$course")
            },
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = course,
            color = MaterialTheme.colors.primary,
            fontSize = 16.sp
        )
    }
}

// Get course names for specific programs
fun getCoursesForProgram(selectedProgram: String): List<String> {
    return when (selectedProgram) {
        "Software Engineering Technology" -> listOf("Web Application Development", "Data Structures and Algorithms", "Mobile Apps Development")
        "Artificial Intelligence Software Engineering" -> listOf("Introduction to Artificial Intelligence", "Supervised Learning", "Neural Networks")
        "Computer Systems Technology" -> listOf("Introduction to Telephony", "Virtualization and Cloud Technology", "Network Security")
        else -> emptyList()
    }
}

// Define the layout for each course description
@Composable
fun CourseDescription(course: String) {

    val courseDescription = getCourseDescription(course)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Course Description:",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = courseDescription,
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .padding(top = 20.dp),
            fontSize = 17.sp,
            color = Color.Black,
            style = TextStyle(
                lineHeight = 24.sp)
        )
    }
}

// Get course descriptions for specific courses
fun getCourseDescription(course: String): String {
    return when (course) {
        "Web Application Development" -> "Web Application Development is the third course in a sequence of web courses, following COMP213 Web Interface Design and COMP125 Client-Side Web Development. In this course, students will learn how to design, code, and test a full-stack web application. The major topics covered in COMP229 include frontend and backend development, connecting to various databases or data stores to query or manipulate data and display the results in the browser, and securing and deploying complex web applications on production servers. Students will apply various open-source frontend and backend frameworks to build a web application and API."
        "Data Structures and Algorithms" -> "Building on fundamentals of Object-Oriented programming, this course exposes the students to algorithms and data structures. Students will analyze, evaluate and apply appropriate data structures & algorithms for the implementation of a software system. Coursework emphasizes the classical data structures, basic algorithm design, common operations on data structures, and the use of mathematical techniques to analyze the efficiency of the various algorithms. The languages of instruction are Java and Python (optional)."
        "Mobile Apps Development" -> "In this mobile apps course, students will gain hands-on experience in developing and deploying mobile applications on the Android platform. Coursework emphasizes how to create advanced Graphical User Interfaces (GUIs), handle events, access remote services, store and retrieve data on the device, display maps, and use other Android APIs. Android Studio will be used to create a variety of mobile applications."
        "Introduction to Artificial Intelligence" -> "In this course, students will be introduced to the history and fundamental concepts of artificial intelligence. Coursework will emphasize types of artificial intelligence, search algorithms, basic machine learning algorithms and ethical aspects of AI."
        "Supervised Learning" -> "In this course, students will be introduced to supervised learning techniques and algorithms. Coursework covers the following algorithms: linear regression, logistic regression, decision trees, bayesian learning, support vector machines, sequence learning, k-nearest neighbors, and ensemble techniques. The concepts of underfitting, overfitting, cross-validation, and kernel methods will be covered throughout the course. Students will practice building an end-to-end supervised learning project."
        "Neural Networks" -> "This course covers artificial neural networks and their practical applications. Coursework emphasizes fundamental models and algorithms starting with McCulloch-Pitts and Perceptron models, Multi-Layer Perceptron (MLP) networks, backpropagation algorithm, activation functions, convolutional neural networks, and recurrent neural networks. Students will gain hands-on experience by using Keras and TensorFlow to build and train models for solving various classification/prediction problems."
        "Introduction to Telephony" -> "Introduction to Telephony guides the student through the topology, architecture, and standards of today's telecommunication networks that transport voice, images, video and data to any location in the world. The course builds on traditional voice telephony and introduces modern converged networks. Groups of students will configure and test Key Systems and Avaya PBXs in several simulated business environments. Practical lab applications include: backup and restoration of PBX translations, dial plan analysis, Moves, Adds, Changes and Deletes of stations, Uniform Dial Plan, Automated Route Selection and Automatic Alternate Routing."
        "Virtualization and Cloud Technology" -> "This course covers the full stack virtualization architecture that combines virtual machines, virtual networking, and storage and how the virtualization technologies support cloud computing. It discusses the concepts, architectures and technologies of cloud computing, examines the benefits, risks and challenges of cloud services as an IT strategy, characterizes different cloud service models, and describes different deployment models of cloud computing. It provides a deep-down analysis of architectures and mechanisms that businesses need to know in order to plan for building and/or migrating IT infrastructure　on cloud. It will also provide the opportunity to first hand experience real world cloud platforms such as Amazon AWS, Microsoft Azure and Google Cloud Platform."
        "Network Security" -> "Quality security is paramount to e-commerce success. In CNET221, learners develop an understanding of real-world network security issues in order to build secure networks. Tools such as cryptography, firewalls and other security techniques will challenge students. Students will learn about the basics of network security, the security problems that may occur in networks including aspects of internet security and solutions."
        else -> "No description available."
    }
}