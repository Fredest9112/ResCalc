package com.example.resistorcalc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.resistorcalc.R
import com.example.resistorcalc.databinding.FragmentResistorCalcBinding

class ResistorCalcFragment : Fragment() {

    private var binding:FragmentResistorCalcBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentResistorCalcBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.detailsButton?.setOnClickListener {
            goToDetailsScreen()
        }

    }

    private fun goToDetailsScreen() {
        findNavController().navigate(R.id.action_resistorCalcFragment_to_resistorDetailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}