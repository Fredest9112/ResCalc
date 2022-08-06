package com.itc.resistorcalc.view.numsysconvers

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.itc.resistorcalc.databinding.FragmentNumSysConversBinding
import com.itc.resistorcalc.model.numsysconvers.NumericSystemConversViewModel

class NumSysConversFragment : Fragment() {

    private var binding: FragmentNumSysConversBinding? = null
    private val numericSystemConversViewModel: NumericSystemConversViewModel by viewModels()

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
            viewModel = numericSystemConversViewModel
            lifecycleOwner = viewLifecycleOwner
            numInput.setOnKeyListener { v, keyCode, _ ->
                handleKeyEvent(v, keyCode)
            }
        }

        binding?.apply {
            numericSystemConversViewModel.apply {
                numInput.doOnTextChanged { enteredText, _, _, _ ->
                    setNumInput(enteredText.toString())
                }
            }
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}