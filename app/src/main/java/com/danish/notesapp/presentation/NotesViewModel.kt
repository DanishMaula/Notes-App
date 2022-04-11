package com.danish.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.danish.notesapp.data.NotesRepository
import com.danish.notesapp.data.local.entity.Notes
import com.danish.notesapp.data.local.room.NotesDao
import com.danish.notesapp.data.local.room.NotesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application : Application) : AndroidViewModel(application){
    private val notesDao: NotesDao = NotesDataBase.getDataBase(application).notesDao()
    private val notesRepository: NotesRepository = NotesRepository(notesDao)

    val sortByHighPriority: LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority: LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun getAllNotes() : LiveData<List<Notes>> = notesRepository.getNotes

    fun insertNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(notes)
        }
    }

    fun searchNoteByQuery(query: String) : LiveData<List<Notes>> {
        return notesRepository.searchNoteByQuery(query)
    }

    fun deletedAllData() {
        viewModelScope.launch (Dispatchers.IO) {
            notesRepository.deletedAllData()
        }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deletedNote(notes)
        }
    }

    fun updateData(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(notes)
        }
    }
}