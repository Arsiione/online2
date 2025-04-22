package com.example.online.utils

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class ReminderWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        // Здесь будет логика отправки уведомления
        return Result.success()
    }
}

fun scheduleReminder(context: Context) {
    val reminderWork = OneTimeWorkRequestBuilder<ReminderWorker>()
        .setInitialDelay(1, TimeUnit.DAYS)
        .build()

    WorkManager.getInstance(context).enqueue(reminderWork)
} 