package com.scube.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() {
    private var restTimer : CountDownTimer?=null
    private var restProgress = 0
    private var exerciseTimer : CountDownTimer?=null
    private var exerciseProgress = 0
    private val exerciseTimeToComlete= 3
    private val restTimeForNextExercise=3
    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition =-1
    private var exerciseAdapter :ExerciseStatusAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(exercise_toolbar)
        val actionBar = supportActionBar
        if(actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        exercise_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        exerciseList = Constants.defaultExerciseList()
        setupRestView()
        setupExerciseStatusRecyclerView()
    }

    override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        if(exerciseTimer!=null){
           exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        super.onDestroy()
    }
    private fun setRestProgressBar(){
        progressBar.progress= restProgress
        restTimer = object :CountDownTimer((restTimeForNextExercise*1000+1000).toLong(),1000){
            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()
            }

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress=restTimeForNextExercise-restProgress
                tvTimer.text=(restTimeForNextExercise-restProgress).toString()
            }

        }.start()
    }
    private fun setExerciseProgressBar(){
        progressBarExercise.progress= exerciseProgress
        exerciseTimer = object :CountDownTimer((exerciseTimeToComlete*1000+1000).toLong(),1000){
            override fun onFinish() {
                progressBarExercise.progress= exerciseProgress
            if(currentExercisePosition<exerciseList?.size!!-1){
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsCompleted(true)
                setupRestView()
            }else{
                Toast.makeText(this@ExerciseActivity,
                "congratulations",Toast.LENGTH_SHORT).show()
            }
            }

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress=exerciseTimeToComlete-exerciseProgress
                tvExerciseTimer.text=(exerciseTimeToComlete-exerciseProgress).toString()
            }

        }.start()
    }
    private fun setupRestView(){
        llRestView.visibility= View.VISIBLE

        llExerciseView.visibility=View.GONE
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        tvUpComingExerciseName.text = exerciseList!![currentExercisePosition+1].getName()
//        tvUpComing.text="Upcoming Exercise"
        setRestProgressBar()
    }
    private fun setupExerciseView(){
        llRestView.visibility= View.GONE
        llExerciseView.visibility=View.VISIBLE

        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }
        setExerciseProgressBar()
        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text= exerciseList!![currentExercisePosition].getName()
    }
    private fun setupExerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,
            false)
        exerciseAdapter= ExerciseStatusAdapter(exerciseList!!,this)
        rvExerciseStatus.adapter =exerciseAdapter

    }

}