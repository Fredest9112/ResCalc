package com.itc.resistorcalc.view.resistorcalc

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.itc.resistorcalc.data.InputValidator.checkInputColorToValue
import com.itc.resistorcalc.data.InputValidator.isValidInput
import com.itc.resistorcalc.R
import com.itc.resistorcalc.databinding.FragmentResistorCalcBinding
import com.itc.resistorcalc.model.ResCalcViewModel
import com.itc.resistorcalc.viewutils.MenuDropDownSetup.setDropDownMenu

class ResistorCalcFragment : Fragment() {

    private var binding: FragmentResistorCalcBinding? = null
    private val resCalcViewModel: ResCalcViewModel by activityViewModels()

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
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = resCalcViewModel
            resistorCalcFragment = this@ResistorCalcFragment
            ervValueInput.setOnKeyListener { v, keyCode, _ ->
                handleKeyEvent(v, keyCode)
            }
        }

        binding?.apply {
            resCalcViewModel.apply {
                color1Selection.setOnItemClickListener { parent, _, position, _ ->
                    setBand1(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                color2Selection.setOnItemClickListener { parent, _, position, _ ->
                    setBand2(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                color3Selection.setOnItemClickListener { parent, _, position, _ ->
                    setBand3(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                multiplierSelection.setOnItemClickListener { parent, _, position, _ ->
                    setMultiplier(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                toleranceSelection.setOnItemClickListener { parent, _, position, _ ->
                    setTolerance(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                ppmSelection.setOnItemClickListener { parent, _, position, _ ->
                    setPPM(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                bandsOptions.setOnCheckedChangeListener { radioGroup, _ ->
                    when (radioGroup.checkedRadioButtonId) {
                        R.id.four_bands -> {
                            setNoOfBands(4)
                            setResultForColors()
                        }
                        R.id.five_bands -> {
                            setNoOfBands(5)
                            setResultForColors()
                        }
                        R.id.six_bands -> {
                            setNoOfBands(6)
                            setResultForColors()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            setDropDownMenu(
                context,
                color1Selection,
                resources.getStringArray(R.array.color_options)
            )
            setDropDownMenu(
                context,
                color2Selection,
                resources.getStringArray(R.array.color_options)
            )
            setDropDownMenu(
                context,
                color3Selection,
                resources.getStringArray(R.array.color_options)
            )
            setDropDownMenu(
                context,
                multiplierSelection,
                resources.getStringArray(R.array.multiplier_options)
            )
            setDropDownMenu(
                context,
                toleranceSelection,
                resources.getStringArray(R.array.tolerance_options)
            )
            setDropDownMenu(
                context,
                ppmSelection,
                resources.getStringArray(R.array.ppm_options)
            )
        }
    }

    fun goToDetailsScreen() {
        binding?.apply {
            val input = checkInputColorToValue(ervValueInput, activity, resources)
            if (isValidInput) {
                val action = ResistorCalcFragmentDirections
                    .actionResistorCalcFragmentToResistorDetailsFragment(
                        ervValue = input
                    )
                findNavController().navigate(action)
                resCalcViewModel.setMaxVal()
                resCalcViewModel.setMinVal()
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