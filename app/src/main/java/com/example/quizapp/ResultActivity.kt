package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USER_NAME)
        val total_question = intent.getIntExtra(Constants.TOTAL_QUESTION, 0)
        val correct_answers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        val tv_username = findViewById<TextView>(R.id.tv_name)
        tv_username.text = username

        val tv_score = findViewById<TextView>(R.id.tv_score)
        tv_score.text = "Your score is $correct_answers out of $total_question"

        val btn_finish = findViewById<Button>(R.id.btn_finish)
        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}