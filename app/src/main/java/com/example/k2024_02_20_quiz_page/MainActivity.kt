package com.example.k2024_02_20_quiz_page

import android.os.Bundle
import androidx.compose.ui.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2024_02_20_quiz_page.controller.NextQuestion
import com.example.k2024_02_20_quiz_page.controller.ScoreController
import com.example.k2024_02_20_quiz_page.model.AllQuestions
import com.example.k2024_02_20_quiz_page.model.Difficulty
import com.example.k2024_02_20_quiz_page.ui.theme.K2024_02_20_quiz_pageTheme
import com.example.k2024_02_20_quiz_page.ui.theme.Pink40

val allQuestions: AllQuestions = AllQuestions()
val scoreController: ScoreController = ScoreController("User")

class MainActivity : ComponentActivity() {

    val nextQuestion: NextQuestion = NextQuestion()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            K2024_02_20_quiz_pageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizPage("Fellow 3200ers", nextQuestion, scoreController)
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun QuizPage(name: String, nextQuestion: NextQuestion, scoreController: ScoreController, modifier: Modifier = Modifier) {
    var questionNumber by remember { mutableStateOf(nextQuestion.getQuestionNumber())}
    var currentScore by remember { mutableStateOf(scoreController.getScore())}
    var userResponse by remember { mutableStateOf<String?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = allQuestions.getQuestion(questionNumber).questionText,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = {
                    val isCorrect = allQuestions.getQuestion(questionNumber).answer
                    userResponse = if (isCorrect) "Correct" else "Wrong"
                    if (isCorrect) {
                        scoreController.incrementScore(Difficulty.EASY)
                    }
                    questionNumber = nextQuestion.getNextRandomQuestionNumber()
                }) {
                    Text("True")
                }
                Button(onClick = {
                    val isCorrect = !allQuestions.getQuestion(questionNumber).answer
                    userResponse = if (isCorrect) "Correct" else "Wrong"
                    if (isCorrect) {
                        scoreController.incrementScore(Difficulty.EASY)
                    }
                    questionNumber = nextQuestion.getNextRandomQuestionNumber()
                }) {
                    Text("False")
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = {
                userResponse = null
                questionNumber = nextQuestion.getNextRandomQuestionNumber()
            }) {
                Text("Next")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = {
                // Assume this is the end of the quiz, you can navigate to the result screen or perform other actions.
                // For now, just reset the question number and display the final score.
                questionNumber = 0
                currentScore = scoreController.getScore()
            }) {
                Text("Done")
            }
        }
        // Display "Correct" or "Wrong" based on the user's response
        userResponse?.let {
            Text(it)
        }
        // Display the final score when the quiz is done
        if (questionNumber == 0) {
            Text("Quiz Completed! Your Final Score: $currentScore", fontSize = 20.sp)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun QuizPagePreview() {
    K2024_02_20_quiz_pageTheme {
        QuizPage("User", NextQuestion(), ScoreController("User"))
    }
}
