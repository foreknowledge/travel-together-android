package com.mungziapp.traveltogether.item

data class DetailScheduleItem (
		val isMove: Boolean? = false,
		val title: String,
		val time: String?,
		val place: String?,
		val memo: String?,
		val photos: String?
)