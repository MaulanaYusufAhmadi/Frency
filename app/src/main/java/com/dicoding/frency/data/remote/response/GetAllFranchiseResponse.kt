package com.dicoding.frency.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetAllFranchiseResponse(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class Franchisor(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class DataItem(

    @field:SerializedName("franchise_name")
    val franchiseName: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("franchisor")
    val franchisor: Franchisor,

    @field:SerializedName("franchiseType")
    val franchiseType: List<FranchiseTypeItem>,

    @field:SerializedName("gallery")
    val gallery: List<GalleryItem>,

    @field:SerializedName("whatsapp_number")
    val whatsappNumber: String
)

data class FranchiseTypeItem(

    @field:SerializedName("type")
    val type: Type
)


data class GalleryItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("franchise_id")
    val franchiseId: Int,

    @field:SerializedName("id")
    val id: Int
)

data class Type(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("franchise_type")
    val franchiseType: String
)