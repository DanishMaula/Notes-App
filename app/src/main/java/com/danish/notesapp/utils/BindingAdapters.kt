package com.danish.notesapp.utils

import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.danish.notesapp.R
import com.danish.notesapp.data.local.entity.Notes
import com.danish.notesapp.data.local.entity.Priority
import com.danish.notesapp.presentation.home.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

object BindingAdapters {

    @BindingAdapter("android:parsePriorityColor")
    @JvmStatic
    fun parsePriorityColor(cardView : MaterialCardView, priority : Priority){
        when (priority){
          Priority.HIGH -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.pink))
          Priority.MEDIUM -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
          Priority.LOW -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
        }
    }

    @BindingAdapter("android:sendDataToDetail")
    @JvmStatic
    fun sendDataToDetail(view: ConstraintLayout, currentItems : Notes){
        view.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentItems)
            view.findNavController().navigate(action)
        }
    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: AppCompatSpinner, priority: Priority) {
        when (priority) {
            Priority.HIGH -> {
                view.setSelection(0)
            }
            Priority.MEDIUM -> {
                view.setSelection(1)
            }
            Priority.LOW -> {
                view.setSelection(2)
            }
        }
    }

    @BindingAdapter("android:emptydatabase")
    @JvmStatic
    fun emptyDatabase(view: View, emptyDataBase: MutableLiveData<Boolean>){
        when (emptyDataBase.value) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.INVISIBLE
        }
    }

}

