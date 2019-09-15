package com.example.staysafesweetheart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.staysafesweetheart.databinding.CardFragmentSettingsContactBinding
import com.example.staysafesweetheart.persistance.entities.Contact


class ContactsListAdapter(context: Context) :
    RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = CardFragmentSettingsContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    inner class ContactsViewHolder(private val binding: CardFragmentSettingsContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Contact) {
            binding.contact = item
            binding.executePendingBindings()
        }
    }

    internal fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}