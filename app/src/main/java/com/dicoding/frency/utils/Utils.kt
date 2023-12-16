package com.dicoding.frency.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun String.showMessage(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}