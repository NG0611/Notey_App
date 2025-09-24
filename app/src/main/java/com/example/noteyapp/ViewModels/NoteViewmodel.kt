package com.example.noteyapp.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteyapp.Databases.NoteRepository
import com.example.noteyapp.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NoteViewmodel @Inject constructor(private val repository: NoteRepository ) : ViewModel() {
    var notelist = MutableStateFlow<List<Note>>(emptyList())
    var notetitle by mutableStateOf("")
    var notedes by mutableStateOf("")

    init{
        viewModelScope.launch {
            repository.getallnotes().collect{listofnotes->
                notelist.value=listofnotes

            }
        }


    }
    fun insertnote(note:Note){
        viewModelScope.launch{
             repository.insertnote(note)

        }

    }
     fun deletenote(note: Note){
         viewModelScope.launch {
             repository.deletenote(note)

         }

    }

}