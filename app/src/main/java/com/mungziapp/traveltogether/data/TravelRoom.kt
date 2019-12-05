package com.mungziapp.traveltogether.data

data class TravelRoom (
        val id: Int,
        val name: String,
        val start_date: String?,
        val end_date: String?,
        val country_codes: String?,
        val thumb: Int?,
        val members: Int?
)