package com.sangmeebee.teamfreshproject.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}

fun String.convertDateFormat(format1: String, format2: String): String {
    val dateFormat1 = SimpleDateFormat(format1, Locale.getDefault())
    val dateFormat2 = SimpleDateFormat(format2, Locale.getDefault())
    val date = dateFormat1.parse(this)
    return dateFormat2.format(date)
}
