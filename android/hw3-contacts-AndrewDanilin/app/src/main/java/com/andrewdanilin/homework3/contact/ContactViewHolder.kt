package com.andrewdanilin.homework3.contact

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrewdanilin.homework3.R

class ContactViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    val nameView = root.findViewById<TextView>(R.id.name)
    val phoneNumberView = root.findViewById<TextView>(R.id.phoneNumber)

    fun bind(contact: Contact) {
        nameView.text = contact.name
        phoneNumberView.text = contact.phoneNumber
    }
}