package com.example.newsapi.API.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapi.R
import com.example.newsapi.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    lateinit var viewBinding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =FragmentSettingsBinding.inflate(inflater,container,false)
        return viewBinding.root
    }


}