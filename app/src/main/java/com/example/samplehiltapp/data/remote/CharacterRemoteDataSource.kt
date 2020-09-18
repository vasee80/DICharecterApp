package com.example.samplehiltapp.data.remote

class CharacterRemoteDataSource constructor(private val characterService: CharacterService): BaseDataSource() {
    suspend fun getCharacters() = getResult { characterService.getAllCharacters() }
    suspend fun getCharacter(id : Int) = getResult { characterService.getCharacter(id) }
}