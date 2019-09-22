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
        binding.alertButton.setOnClickListener { onAlert() }
        return binding.root
    }

    private fun onAlert() {

    }
}
