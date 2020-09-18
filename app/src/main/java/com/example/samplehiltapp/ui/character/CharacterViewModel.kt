package com.example.samplehiltapp.ui.character

import androidx.lifecycle.ViewModel
import com.example.samplehiltapp.data.repository.CharacterRepository

class CharacterViewModel constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}