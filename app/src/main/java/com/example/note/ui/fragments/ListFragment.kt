package com.example.note.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.ui.adapters.NotesAdapter
import com.example.note.ui.listeners.RecyclerItemClickListener
import com.example.note.viewmodel.NotesListViewModel

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val viewModel = ViewModelProvider(requireActivity()).get(NotesListViewModel::class.java)
        val adapter = NotesAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        if (savedInstanceState == null) {
            viewModel.requestNotes()
        }

        viewModel.getNotesLiveData().observe(viewLifecycleOwner) {
            adapter.addNotes(it)
            adapter.notifyDataSetChanged()
        }

        val manager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        var scrollPosition = 0

        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        with(recyclerView) {
            addOnItemTouchListener(RecyclerItemClickListener(
                context,
                this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClicked(view: View, position: Int) {
                        viewModel.onNoteClicked(position)
                    }

                    override fun onLongItemClicked(view: View, position: Int) {
                        registerForContextMenu(view)
                    }
                }
            ))
            layoutManager = manager
            scrollToPosition(scrollPosition)
            setAdapter(adapter)
        }

        return view
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_list_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                //TODO(delete note)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}