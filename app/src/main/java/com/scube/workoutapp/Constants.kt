package com.scube.workoutapp

class Constants {
    companion object{
        fun defaultExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =ExerciseModel(1, "knee sit ups",R.drawable.ic_knee_sit_up,false,false)
            exerciseList.add(jumpingJacks)
            val abdominalCrunch =ExerciseModel(2, "sit ups",R.drawable.ic_sit_ups,false,false)
            exerciseList.add(abdominalCrunch)
            val highKneesRun =ExerciseModel(3, "high knee running",R.drawable.ic_knee_running,false,false)
            exerciseList.add(highKneesRun)
            val lunge =ExerciseModel(4, "lunge",R.drawable.ic_lunges,false,false)
            exerciseList.add(lunge)
            val plank =ExerciseModel(5, "Planks",R.drawable.ic_plank,false,false)
            exerciseList.add(plank)
            val pushUp =ExerciseModel(6, "push ups",R.drawable.ic_push_up,false,false)
            exerciseList.add(pushUp)
            val pushUpAndRotation =ExerciseModel(7, "pilates",R.drawable.ic_pilates,false,false)
            exerciseList.add(pushUpAndRotation)
            val sidePlanks =ExerciseModel(8, "side planks",R.drawable.ic_side_plank,false,false)
            exerciseList.add(sidePlanks)
            val squate =ExerciseModel(9, "squate",R.drawable.ic_squat,false,false)
            exerciseList.add(squate)
            val tricepOntoChair =ExerciseModel(10, "tricep onto chair",R.drawable.ic_triceps,false,false)
            exerciseList.add(tricepOntoChair)
            val wallSit =ExerciseModel(11, "meditation",R.drawable.ic_meditation,false,false)
            exerciseList.add(wallSit)
            return exerciseList
        }
    }
}