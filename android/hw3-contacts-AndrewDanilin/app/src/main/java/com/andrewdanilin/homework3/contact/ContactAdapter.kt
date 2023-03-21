package com.andrewdanilin.homework3.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework3.R

class ContactAdapter(
    private val contacts: List<Contact>,
    private val onClick: (Contact) -> Unit
): RecyclerView.Adapter<ContactViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val holder = ContactViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )

        holder.nameView.setOnClickListener {
            onClick(contacts[holder.adapterPosition])
        }

        return holder
    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) = holder.bind(contacts[position])

    override fun getItemCount() = contacts.size
}