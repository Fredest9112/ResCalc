package com.example.resistorcalc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.resistorcalc.databinding.FragmentResistorDetailsBinding
import com.example.resistorcalc.model.ResCalcViewModel

class ResistorDetailsFragment : Fragment() {

    private var binding: FragmentResistorDetailsBinding? = null
    private val resCalcViewModel : ResCalcViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentResistorDetailsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = resCalcViewModel
            resistorDetailsFrag = this@ResistorDetailsFragment
        }

        arguments?.let {
            resCalcViewModel.setExpResult(it.getFloat("ervValue")) }
        resCalcViewModel.setState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}