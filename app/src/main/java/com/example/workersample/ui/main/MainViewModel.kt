package com.example.workersample.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.workersample.worker.DiceRollWorker
import com.example.workersample.worker.DiceSumWorker
import com.example.workersample.worker.MyCoroutineWorker
import com.example.workersample.worker.MyWorker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    fun startWorkRequests(workManager: WorkManager) {
        workManager.cancelAllWork()

        viewModelScope.launch {
            OneTimeWorkRequest(workManager)
//            PeriodicWorkRequest(15, workManager)

            OneTimeCoroutineWorkRequest(workManager)
//            PeriodicCoroutineWorkRequest(15, workManager)
        }

        viewModelScope.launch {
            delay(1000L)
            Log.e("-", "-----------------------------------------------------------------------------")
            ArrayCreatingInputMerger(workManager)
        }
    }

    fun OneTimeWorkRequest(workManager: WorkManager) {
        /*단발 WorkRequest*/
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        workManager.enqueue(workRequest)
    }

    fun PeriodicWorkRequest(period: Long, workManager: WorkManager) {
        /*주기적으로 반복하는 WorkRequest*/
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(period, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun OneTimeCoroutineWorkRequest(workManager: WorkManager) {
        /*단발 CoroutineWorkRequest*/
        val workRequest = OneTimeWorkRequestBuilder<MyCoroutineWorker>().build()
        workManager.enqueue(workRequest)
    }

    fun PeriodicCoroutineWorkRequest(period: Long, workManager: WorkManager) {
        /*주기적으로 반복하는 CoroutineWorkRequest*/
        val workRequest = PeriodicWorkRequestBuilder<MyCoroutineWorker>(period, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun ArrayCreatingInputMerger(workManager: WorkManager) {
        val diceRollWorker1 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()
        val diceRollWorker2 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()
        val diceRollWorker3 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()

        val diceSumWorker = OneTimeWorkRequestBuilder<DiceSumWorker>()
                .setInputMerger(ArrayCreatingInputMerger::class)
                .build()

        workManager.beginWith(listOf(diceRollWorker1, diceRollWorker2, diceRollWorker3))
                .then(diceSumWorker).enqueue()
    }
}