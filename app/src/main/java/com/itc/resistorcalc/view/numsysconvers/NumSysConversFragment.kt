package com.itc.resistorcalc.view.numsysconvers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itc.resistorcalc.R
import com.itc.resistorcalc.databinding.FragmentNumSysConversBinding
import com.itc.resistorcalc.viewutils.MenuDropDownSetup.setDropDownMenu

class NumSysConversFragment : Fragment() {

    private var binding: FragmentNumSysConversBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentNumSysConversBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            setDropDownMenu(
                context,
                numSysSelector,
                resources.getStringArray(R.array.numeric_system_options)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}