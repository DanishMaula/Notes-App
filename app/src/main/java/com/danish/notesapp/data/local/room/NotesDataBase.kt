package com.danish.notesapp.data.local.room

import android.content.Context
import androidx.room.*
import com.danish.notesapp.data.local.entity.Notes


@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun notesDao() : NotesDao

    companion object{
        @Volatile
        var instance : NotesDataBase? = null

        @JvmStatic
        fun getDataBase(context : Context) : NotesDataBase {
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context,
                        NotesDataBase::class.java,
                        "notes.db"
                    ).build()
                }
            }
            return instance as NotesDataBase
        }
    }
}