package com.danish.notesapp.data

import androidx.lifecycle.LiveData
import com.danish.notesapp.data.local.entity.Notes
import com.danish.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {



    val getNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
    val sortByHighPriority : LiveData<List<Notes>> = notesDao.sortByHighPriority()
    val sortByLowPriority : LiveData<List<Notes>> = notesDao.sortByLowPriority()

     fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(query : String): LiveData<List<Notes>>{
        return notesDao.searchNoteByQuery(query)
    }

    suspend fun deletedAllData() {
        notesDao.deleteAllData()
    }

    suspend fun deletedNote (notes: Notes){
        notesDao.deletedNote(notes)
    }

    suspend fun updateNote(notes: Notes){
       return notesDao.updateNote(notes)
    }

}