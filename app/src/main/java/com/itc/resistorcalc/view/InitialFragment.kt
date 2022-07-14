package com.itc.resistorcalc.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.itc.resistorcalc.MyApp
import com.itc.resistorcalc.databinding.FragmentInitialBinding
import com.itc.resistorcalc.model.resistorcalc.ResCalcViewModel
import com.itc.resistorcalc.model.resistorcalc.ResCalcViewModelFactory
import javax.inject.Inject

class InitialFragment : Fragment() {

    private var binding: FragmentInitialBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentInitialBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            initialFragment = this@InitialFragment
        }
    }

    fun goToResistorCalcFragment() {
        val action = InitialFragmentDirections.actionInitialFragmentToResistorCalcFragment()
        findNavController().navigate(action)
    }

    fun goToFromValueResistorFragment() {
        val action = InitialFragmentDirections.actionInitialFragmentToFromValueResistorFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}