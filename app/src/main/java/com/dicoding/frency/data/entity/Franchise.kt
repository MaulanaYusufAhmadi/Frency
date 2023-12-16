package com.dicoding.frency.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Franchise(
    var name: String,
    var address: String,
    var description: String,
    var category: String,
    var whatsappNumber: String,
    var type: Array<String>,
    var imgUrl: Array<String>
) : Parcelable