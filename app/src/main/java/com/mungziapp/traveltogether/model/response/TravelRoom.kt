package com.mungziapp.traveltogether.model.response

data class TravelRoom (
		val id: String,
		val name: String,
		val startDate: String?,
		val endDate: String?,
		val coverImagePath: String,
		val members: List<Member>,
		val countries: List<Country>
)