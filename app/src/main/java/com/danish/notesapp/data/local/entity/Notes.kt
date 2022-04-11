package com.danish.notesapp.data.local.entity

import android.os.Parcelable
import android.renderscript.RenderScript
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "tb_notes")
@Parcelize
data class Notes (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val priority: Priority

) : Parcelable