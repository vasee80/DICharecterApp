package com.example.samplehiltapp.ui.character

import androidx.lifecycle.ViewModel
import com.example.samplehiltapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}