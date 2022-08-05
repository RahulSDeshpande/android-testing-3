package com.rahulografy.testing3.playlists.di

import com.rahulografy.testing3.playlists.PlaylistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class PlaylistsModule {

    @Provides
    @Singleton
    fun providePlaylistsApi(retrofit: Retrofit): PlaylistsApi =
        retrofit.create(PlaylistsApi::class.java)

    @Provides
    @Singleton
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://a641e3c6-796c-438e-a2b9-454ca439269d.mock.pstmn.io")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
