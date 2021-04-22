package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    lateinit var progressBar: ProgressBar
    lateinit var tv_progress: TextView
    lateinit var tv_question: TextView
    lateinit var iv_image: ImageView
    lateinit var tv_option_one: TextView
    lateinit var tv_option_two: TextView
    lateinit var tv_option_three: TextView
    lateinit var tv_option_four: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_question = findViewById<TextView>(R.id.tv_question)
        iv_image = findViewById<ImageView>(R.id.iv_image)
        tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        tv_option_four = findViewById<TextView>(R.id.tv_option_four)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    private fun setQuestion() {
        mCurrentPosition = 1
        val question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition/${progressBar.max}"
        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        options.forEach {
            it.setTextColor(Color.parseColor("#7a8089"))
            it.typeface = Typeface.DEFAULT
            it.background = ContextCompat.getDrawable(
                    this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            tv_option_one.id -> selectedOptionView(tv_option_one, 1)
            tv_option_two.id -> selectedOptionView(tv_option_two, 2)
            tv_option_three.id -> selectedOptionView(tv_option_three, 3)
            tv_option_four.id -> selectedOptionView(tv_option_four, 4)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
                this, R.drawable.selected_option_border_bg)
    }
}