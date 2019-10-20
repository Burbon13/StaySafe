package com.example.staysafesweetheart.fragments.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.dagger2.StaySafeComponent
import com.example.staysafesweetheart.databinding.FragmentTemplateMessageBinding
import com.example.staysafesweetheart.viewmodel.settings.template.message.TemplateMessageViewModel
import com.example.staysafesweetheart.viewmodel.settings.template.message.TemplateMessageViewModelFactory
import javax.inject.Inject


class TemplateMessageFragment : Fragment() {

    companion object {
        private val TAG = TemplateMessageFragment::class.qualifiedName
    }

    @Inject
    lateinit var templateMessageViewModelFactory: TemplateMessageViewModelFactory

    private lateinit var binding: FragmentTemplateMessageBinding
    private lateinit var templateMessageViewModel: TemplateMessageViewModel
    private lateinit var daggerComponent: StaySafeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        daggerComponent =
            DaggerStaySafeComponent.builder().settingsModule(SettingsModule(context!!)).build()
        daggerComponent.inject(this)
        templateMessageViewModel = ViewModelProvider(
            this,
            templateMessageViewModelFactory
        ).get(TemplateMessageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_template_message,
            container,
            false
        )

        binding.templateMessageViewModel = templateMessageViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
        //ONCE ONCE ONCE
        templateMessageViewModel.templateMessage.observe(this, Observer { templateMessage ->
            binding.templateMessagesEditText.setText(templateMessage?.text)
        })
        templateMessageViewModel.templateMessageTextError.observe(this, Observer { error ->
            if (error) {
                binding.templateMessagesEditText.error = "Invalid template message"
            }
        })
        templateMessageViewModel.templateMessageTextSaved.observe(this, Observer { saved ->
            if (saved) {
                findNavController().popBackStack()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
        templateMessageViewModel.templateMessageTextError.removeObservers(this)
        templateMessageViewModel.templateMessageTextSaved.removeObservers(this)
    }
}
