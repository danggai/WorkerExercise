package com.example.workersample.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MyCoroutineWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        /* 처리해야할 작업에 관한 코드들 */
        delay(500L)
        Log.e("MyCoroutineWorker", "Hello!")
        return Result.success()
    }
}