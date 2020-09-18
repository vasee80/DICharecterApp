package com.example.samplehiltapp.ui.character

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.samplehiltapp.data.repository.CharacterRepository

class CharacterViewModel @ViewModelInject constructor(
    val repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}