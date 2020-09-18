package com.example.samplehiltapp.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.samplehiltapp.data.repository.CharacterRepository
import com.example.samplehiltapp.util.Resource
import com.example.samplehiltapp.data.entities.Character

class CharacterDetailViewModel constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<Character>> = _character


    fun start(id: Int) {
        _id.value = id
    }

}