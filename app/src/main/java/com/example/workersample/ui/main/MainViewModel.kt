package com.example.workersample.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workersample.worker.MyCoroutineWorker
import com.example.workersample.worker.MyWorker
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

}