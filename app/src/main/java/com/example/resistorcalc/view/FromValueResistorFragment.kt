package com.example.resistorcalc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.resistorcalc.R
import com.example.resistorcalc.databinding.FragmentFromValueResistorBinding
import com.example.resistorcalc.view.MenuDropDownSetup.setDropDownMenu

class FromValueResistorFragment : Fragment() {

    private var binding:FragmentFromValueResistorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFromValueResistorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            fromValueResistorFragment = this@FromValueResistorFragment
            setDropDownMenu(requireContext(), valueToleranceSelect,
                resources.getStringArray(R.array.tolerance_options))
            setDropDownMenu(requireContext(), valuePpmSelect,
                resources.getStringArray(R.array.ppm_options)
            )
            valueToleranceSelect.text.toString()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}