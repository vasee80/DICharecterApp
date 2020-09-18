package com.example.samplehiltapp.data.repository

import com.example.samplehiltapp.data.local.CharacterDao
import com.example.samplehiltapp.data.remote.CharacterRemoteDataSource
import com.example.samplehiltapp.util.performGetOperation

class CharacterRepository constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {
    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}