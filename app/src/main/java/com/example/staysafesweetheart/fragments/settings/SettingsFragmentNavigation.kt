package com.example.staysafesweetheart.fragments.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.staysafesweetheart.R


class SettingsFragmentNavigation : Fragment() {

    companion object {
        private val TAG = SettingsFragmentNavigation::class.qualifiedName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_settings_navigation, container, false)
    }

    fun onBackPressed(): Boolean {
        Log.d(TAG, "onBackPressed")
        return findNavController().popBackStack()
    }
}
