package com.danish.notesapp.presentation.detail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.danish.notesapp.MainActivity
import com.danish.notesapp.R
import com.danish.notesapp.databinding.FragmentDetailBinding
import com.danish.notesapp.presentation.NotesViewModel
import com.danish.notesapp.utils.ExtensionFuctions.setActionBar


class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()
    private val detailViewModel by viewModels<NotesViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.toolbarDetail.setActionBar(requireActivity())
        binding.detail = args

        Log.d("detail", args.notes.title)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit -> {
                val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(args.notes)
                findNavController().navigate(action)
            }
            R.id.menu_delete -> confirmDeletedNote()
        }
        return super .onOptionsItemSelected(item)
    }

    private fun confirmDeletedNote() {
        AlertDialog.Builder(context)
            .setTitle("Delete '${args.notes.title}'?")
            .setMessage("Are you sure want to remove '${args.notes.title}'?")
            .setPositiveButton("Yes") { _, _ ->
                detailViewModel.deleteNote(args.notes)
                Toast.makeText(context, "Successfully deleted note.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
            .setNegativeButton("No") { _, _ -> }
            .setNeutralButton("Cancel") {_, _ ->}
            .show()
    }

}