package com.example.note.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.viewmodel.NotesListViewModel
import com.google.android.material.textfield.TextInputEditText

class EditableFragment : Fragment() {
    private lateinit var viewModel: NotesListViewModel
    private var editNoteDescription: EditText? = null
    private var editNoteTitle: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_editable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NotesListViewModel::class.java)
        editNoteTitle = view.findViewById(R.id.edit_note_title)
        editNoteDescription = view.findViewById(R.id.edit_note_description)

        editNoteTitle?.addTextChangedListener(TextWatcher())
        editNoteDescription?.addTextChangedListener(TextWatcher())

        viewModel.getSelectedNote().observe(viewLifecycleOwner) { note ->
            editNoteTitle?.setText(note.title)
            editNoteDescription?.setText(note.description)
        }
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.noteClose()
    }

    class TextWatcher : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            //TODO(save text)
        }
    }
}