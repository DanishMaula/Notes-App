package com.danish.notesapp.presentation.add

import android.os.Bundle
import android.view.*
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.danish.notesapp.MainActivity
import com.danish.notesapp.R
import com.danish.notesapp.data.local.entity.Notes
import com.danish.notesapp.data.local.entity.Priority
import com.danish.notesapp.databinding.FragmentAddBinding
import com.danish.notesapp.presentation.NotesViewModel
import com.danish.notesapp.utils.ExtensionFuctions.setActionBar
import com.danish.notesapp.utils.HelperFunctions.parseToPriority
import com.danish.notesapp.utils.HelperFunctions.setPriorityIndicator
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    private var _binding : FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

    private val addViewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding  = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)


        binding.apply {
            toolbarAdd.setActionBar(requireActivity())
            spinnerPriorities.onItemSelectedListener =
                context?.let { setPriorityIndicator(it, priorityIndicator) }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNote()

        }
    }

    private fun insertNote() {
        binding.apply {
            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()

            val calendar = Calendar.getInstance().time
            val priority = spinnerPriorities.selectedItem.toString()
            val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calendar)

            if(edtTitle.text.isEmpty() || edtDescription.text.isEmpty()) {
                if (edtTitle.text.isEmpty()){
                    edtTitle.error = "Please fill fields."
                }else if (edtDescription.text.isEmpty()){
                    edtDescription.error = "Please fill fields."
                }else {
                    edtTitle.error = "Please fill field"
                    edtDescription.error = "Please fill field"
                }
                Toast.makeText(context, "Please fill empty fields", Toast.LENGTH_SHORT).show()
                edtTitle.error = "Please fill empty fields"
            }else {
                val data = Notes(
                    0,
                    title,
                    desc,
                    date,
                    parseToPriority(context, priority)
                )
                addViewModel.insertNotes(data)
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                Toast.makeText(context, "Successfully Add Note.", Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                Toast.makeText(context, "Successfully Add Note.", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}