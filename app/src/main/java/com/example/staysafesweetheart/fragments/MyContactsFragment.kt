package com.example.staysafesweetheart.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.staysafesweetheart.R

import com.example.staysafesweetheart.adapters.ContactsListAdapter
import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.dagger2.StaySafeComponent
import com.example.staysafesweetheart.databinding.FragmentMyContactsBinding
import com.example.staysafesweetheart.viewmodel.MyContactsViewModel
import com.example.staysafesweetheart.viewmodel.MyContactsViewModelFactory
import javax.inject.Inject


class MyContactsFragment : Fragment() {

    @Inject
    lateinit var myContactsViewModelFactory: MyContactsViewModelFactory
    @Inject
    lateinit var contactsListAdapter: ContactsListAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var binding: FragmentMyContactsBinding
    private lateinit var myContactsViewModel: MyContactsViewModel
    private lateinit var daggerComponent: StaySafeComponent

    companion object {
        private val TAG = SettingsFragment::class.qualifiedName
    }

    //Dependency injection and initialization of Fragment’s members takes place here.
    //You must not touch or do anything related to Android Views in Fragment’s onCreate(Bundle)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        daggerComponent =
            DaggerStaySafeComponent.builder().settingsModule(SettingsModule(context!!)).build()

        daggerComponent.inject(this)
        myContactsViewModel =
            ViewModelProvider(this, myContactsViewModelFactory).get(MyContactsViewModel::class.java)
    }

    // All Fragment’s members holding references to objects related to View hierarchy must be
    // assigned inside View onCreateView.
    // In other words, if Fragment holds references to Views or related objects in it’s fields,
    // make sure that all these fields are assigned in this method. This is really important.
    // Every Fragment’s field related to View hierarchy must be assigned in this method.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_contacts,
            container,
            false
        )
        binding.myContactsViewModel = myContactsViewModel
        binding.myContactsRecyclerView.adapter = contactsListAdapter
        binding.myContactsRecyclerView.layoutManager = layoutManager

        return binding.root
    }

    // 1. Registration of View click listeners
    // 2. Subscription to observables (general observables, not necessarily Rx)
    // 3. Reflect the current state into UI (UI update)
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
//        super.onStart()
//        myContactsViewModel.contacts.observe(this, Observer {
//            contactsListAdapter.setContacts(it)
//        })
//        binding.buttonAddContact.setOnClickListener { onAddNewContact() }
    }

    // In this method you will unregister all observers and listeners and release all resources
    // that were allocated in onStart().
    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
//        myContactsViewModel.contacts.removeObservers(this)
//        binding.myContactsViewModel.setOnClickListener(null)
    }


    private fun onAddNewContact() {
        //TODO: Start new fragment
    }
}
