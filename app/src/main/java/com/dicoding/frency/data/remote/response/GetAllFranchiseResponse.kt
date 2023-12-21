package com.dicoding.frency.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetAllFranchiseResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Franchisor(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class DataItem(

	@field:SerializedName("franchise_name")
	val franchiseName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("franchisor")
	val franchisor: Franchisor? = null,

	@field:SerializedName("franchiseType")
	val franchiseType: List<FranchiseTypeItem?>? = null,

	@field:SerializedName("gallery")
	val gallery: List<GalleryItem?>? = null,

	@field:SerializedName("whatsapp_number")
	val whatsappNumber: String? = null
)

data class FranchiseTypeItem(

	@field:SerializedName("franchise_id")
	val franchiseId: Int? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("facility")
	val facility: String? = null,

	@field:SerializedName("franchise_type")
	val franchiseType: String? = null
)

data class GalleryItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("franchise_id")
	val franchiseId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
