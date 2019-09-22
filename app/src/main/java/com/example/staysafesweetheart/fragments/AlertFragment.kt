package com.example.staysafesweetheart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.databinding.FragmentAlertBinding


class AlertFragment : Fragment() {
    private lateinit var binding: FragmentAlertBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alert, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.alertButton.setOnClickListener { onAlert() }
    }

    override fun onStop() {
        super.onStop()
        binding.alertButton.setOnClickListener(null)
    }

    private fun onAlert() {

    }
}
