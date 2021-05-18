package com.example.workersample.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.util.*

class DiceRollWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("DiceRollWorker", "start")

        val random = Random()
        val diceResult = 1 + random.nextInt(6)
        Log.e("DiceRollWorker", "Dice roll result = $diceResult!")
        return Result.success(workDataOf(Pair("DiceResult", diceResult)))
    }
}