package com.mungziapp.traveltogether.app;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloConnector {

	private static final String BASE_URL = "http://192.168.0.16:4000";

	public static ApolloClient setupApollo() {

		OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

		return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
	}
}
