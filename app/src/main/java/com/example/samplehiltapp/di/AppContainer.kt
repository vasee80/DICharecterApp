package com.example.samplehiltapp.di

import android.content.Context
import com.example.samplehiltapp.data.local.AppDatabase
import com.example.samplehiltapp.data.remote.CharacterRemoteDataSource
import com.example.samplehiltapp.data.remote.CharacterService
import com.example.samplehiltapp.data.repository.CharacterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Container of objects shared across the whole app
class AppContainer constructor(private val context: Context){

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
    
    private val gson: Gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(CharacterService::class.java)

    private val remoteDataSource = CharacterRemoteDataSource(retrofit)
    private val localDataSource = AppDatabase.getDatabase(context).characterDao()

    // userRepository is not private; it'll be exposed
    val userRepository = CharacterRepository(remoteDataSource, localDataSource)
}