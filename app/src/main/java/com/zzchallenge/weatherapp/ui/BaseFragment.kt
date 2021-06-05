package com.zzchallenge.weatherapp.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragment : Fragment() {

    fun navigate(direction: NavDirections) =
        findNavController().navigate(direction)

}