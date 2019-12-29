package com.mungziapp.traveltogether.model.response

data class TokenResponse (
		val token: String,
		val payload: Payload,
		val refreshToken: String
)