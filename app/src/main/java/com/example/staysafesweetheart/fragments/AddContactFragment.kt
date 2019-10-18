package com.example.staysafesweetheart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.databinding.FragmentAddContactBinding
import com.example.staysafesweetheart.viewmodel.AddContactViewModel
import com.example.staysafesweetheart.viewmodel.AddContactViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AddContactFragment : Fragment() {
    @Inject
    lateinit var addContactViewModelFactory: AddContactViewModelFactory

    private lateinit var binding: FragmentAddContactBinding
    private lateinit var addContactViewModel: AddContactViewModel

    companion object {
        private val TAG = AddContactFragment::class.qualifiedName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerStaySafeComponent.builder().settingsModule(SettingsModule(context!!)).build()
            .inject(this)

        addContactViewModel =
            ViewModelProvider(this, addContactViewModelFactory).get(AddContactViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_contact,
            container,
            false
        )

        binding.addContactViewModel = addContactViewModel

        return binding.root
    }

    // Ai ramas aici, trebuie sa adaugi observeriiiiiii :>
    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}
