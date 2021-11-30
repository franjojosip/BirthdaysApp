package com.fjjukic.birthdaysapp.base.di

import com.apollographql.apollo.ApolloClient
import com.fjjukic.birthdaysapp.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module

val appModule = module {
    single { ApolloConnector.provideClient() }
}

object ApolloConnector {
    fun provideClient(): ApolloClient {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.builder().serverUrl(BuildConfig.API_URL).okHttpClient(okHttpClient).build()
    }
}