package com.dicoding.frency.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FranchiseItem(
    var type: String,
    var facility: String,
    var price: String
) : Parcelable