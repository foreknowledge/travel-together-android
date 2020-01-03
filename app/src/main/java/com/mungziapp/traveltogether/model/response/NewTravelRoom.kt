package com.mungziapp.traveltogether.model.response

data class NewTravelRoom (
		val id: String,
		val name: String,
		val startDate: String?,
		val endDate: String?,
		val coverImagePath: String?,
		val createdDate: String
)