package com.example.note.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.domain.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private val notes = ArrayList<Note>()

    fun addNotes(notes: List<Note>) {
        val diffResult = DiffUtil.calculateDiff(NotesDiffUtilCallback(this.notes, notes))
        with(this.notes) {
            clear(); addAll(notes)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var noteTitle = view.findViewById<TextView>(R.id.note_title)
        private var noteDate = view.findViewById<TextView>(R.id.note_date)

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteDate.text = note.date.toString()
        }
    }

    class NotesDiffUtilCallback(
        private val oldList: List<Note>,
        private val newList: List<Note>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == (newList[newItemPosition].id)

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}