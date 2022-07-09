package com.example.viewdata

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewdata.databinding.ListItemBinding

class TodoAdapter(private val viewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoAdapter.MyViewHolder, position: Int) {
        holder.todoText.text = viewModel.todos.value!![position].task

        holder.delBtn.setOnClickListener {
            viewModel.todos.value!!.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, viewModel.todos.value!!.size)
        }

        holder.edtBtn.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_item, null)

            val prevText = viewModel.todos.value!![position].task
            val editText = view.findViewById<TextView>(R.id.editText)
            editText.text = prevText

            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Text")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, id ->

                    //edit data
                    viewModel.todos.value!![position].task = editText.text.toString()
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                })

            alertDialog.create().show()
        }
    }

    override fun getItemCount() = viewModel.todos.value!!.size


    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val todoText = binding.tvItem
        val delBtn = binding.btnHapus
        val edtBtn = binding.btnEdit
    }
}

