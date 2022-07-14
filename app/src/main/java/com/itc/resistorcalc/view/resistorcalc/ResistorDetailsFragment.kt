package com.itc.resistorcalc.view.resistorcalc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itc.resistorcalc.MyApp
import com.itc.resistorcalc.R
import com.itc.resistorcalc.databinding.FragmentResistorDetailsBinding
import com.itc.resistorcalc.model.resistorcalc.ResCalcViewModel
import com.itc.resistorcalc.model.resistorcalc.ResCalcViewModelFactory
import com.itc.resistorcalc.view.MainActivity
import javax.inject.Inject

class ResistorDetailsFragment : Fragment() {

    private var binding: FragmentResistorDetailsBinding? = null

    @Inject
    lateinit var resCalcViewModelFactory: ResCalcViewModelFactory

    private val resCalcViewModel by lazy {
        ViewModelProvider(requireActivity(), resCalcViewModelFactory)[ResCalcViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as MyApp).appComponent.injectResistorCalcFragment(this)
    }

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
            resCalcViewModel.setExpResult(it.getFloat("ervValue"))
        }
        resCalcViewModel.setState()
    }

    fun returnToCalc() {
        findNavController().navigate(R.id.action_resistorDetailsFragment_to_resistorCalcFragment)
        resCalcViewModel.apply {
            setInitialState()
            setBntDetailsValidator()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}