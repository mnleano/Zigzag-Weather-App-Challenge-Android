package com.zzchallenge.weatherapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zzchallenge.weatherapp.R
import com.zzchallenge.weatherapp.databinding.FragmentMainBinding
import com.zzchallenge.weatherapp.ui.BaseFragment

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.btn.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToWeatherDetailsFragment())
        }

        return binding.root
    }


}