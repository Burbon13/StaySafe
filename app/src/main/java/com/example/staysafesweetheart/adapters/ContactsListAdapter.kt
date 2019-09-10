package com.example.staysafesweetheart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.persistance.entities.Contact

class ContactsListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            inflater.inflate(
                R.layout.card_fragment_settings_contact,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.contactNameItemView.text = contacts[position].name
        holder.contactEmailItemView.text = contacts[position].email
        holder.contactPhoneItemView.text = contacts[position].phoneNumber
    }

    inner class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactNameItemView: TextView = itemView.findViewById(R.id.text_view_name)
        val contactPhoneItemView: TextView = itemView.findViewById(R.id.text_view_phone)
        val contactEmailItemView: TextView = itemView.findViewById(R.id.text_view_email)
    }

    internal fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}