package com.example.resistorcalc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.example.resistorcalc.R
import com.example.resistorcalc.data.InputValidator.checkInputValueToColor
import com.example.resistorcalc.data.InputValidator.isValidInput
import com.example.resistorcalc.databinding.FragmentFromValueResistorBinding
import com.example.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.example.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.example.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.example.resistorcalc.model.ResCalcViewModel
import com.example.resistorcalc.view.MenuDropDownSetup.setDropDownMenu

class FromValueResistorFragment : Fragment() {

    private var binding: FragmentFromValueResistorBinding? = null
    private val resCalcViewModel: ResCalcViewModel by activityViewModels()

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
            viewModel = resCalcViewModel
            lifecycleOwner = viewLifecycleOwner
            fromValueResistorFragment = this@FromValueResistorFragment

            resCalcViewModel.apply {
                valueToleranceSelect.setOnItemClickListener { parent, _, position, _ ->
                    setTolerance(parent.adapter.getItem(position).toString())
                }
                valuePpmSelect.setOnItemClickListener { parent, _, position, _ ->
                    setPPM(parent.adapter.getItem(position).toString())
                }
            }
        }

        binding?.apply {
            resCalcViewModel.apply {
                band1.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueColor1)
                }
                band2.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueColor2)
                }
                band3.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueColor3)
                }
                multiplier.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, multiplierValue)
                }
                sTolerance.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueTolerance)
                }
                sPPM.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, ppmValue)
                }
            }
        }

        setFourBandsCalc()

        binding?.valueResistInput?.doOnTextChanged { enteredText, _, _, _ ->
            updateResistorValues(enteredText.toString())
        }
    }

    private fun updateResistorValues(enteredText: String) {
        val input = checkInputValueToColor(
            resCalcViewModel.noOfBands.value,
            enteredText,
            activity,
            resources
        )
        if (isValidInput) {
            when (resCalcViewModel.noOfBands.value) {
                FOUR_BANDS -> {
                    resCalcViewModel.setResultForValues(input)
                }
                FIVE_BANDS -> {
                    resCalcViewModel.setResultForValues(input)
                }
                SIX_BANDS -> {
                    resCalcViewModel.setResultForValues(input)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            setDropDownMenu(
                requireContext(), valueToleranceSelect,
                resources.getStringArray(R.array.tolerance_options)
            )
            setDropDownMenu(
                requireContext(), valuePpmSelect,
                resources.getStringArray(R.array.ppm_options)
            )
        }
    }

    fun setCalcState(noOfBands: Int) {
        when (noOfBands) {
            FOUR_BANDS -> {
                setFourBandsCalc()
                resCalcViewModel.apply {
                    setNoOfBands(FOUR_BANDS)
                }
                updateResistorValues(binding?.valueResistInput?.text.toString())
            }
            FIVE_BANDS -> {
                setFiveBandsCalc()
                resCalcViewModel.apply {
                    setNoOfBands(FIVE_BANDS)
                }
                updateResistorValues(binding?.valueResistInput?.text.toString())
            }
            SIX_BANDS -> {
                setSixBandsCalc()
                resCalcViewModel.apply {
                    setNoOfBands(SIX_BANDS)
                }
                updateResistorValues(binding?.valueResistInput?.text.toString())
            }
        }
    }

    private fun setFourBandsCalc() {
        binding?.apply {
            valueFourBands.isChecked = true
            ppmMenu.visibility = View.GONE
            ppmValue.visibility = View.GONE
            valueColor3.visibility = View.GONE
            band3Label.visibility = View.GONE
            ppmValueLabel.visibility = View.GONE
        }
    }

    private fun setFiveBandsCalc() {
        binding?.apply {
            ppmMenu.visibility = View.GONE
            ppmValue.visibility = View.GONE
            valueColor3.visibility = View.VISIBLE
            band3Label.visibility = View.VISIBLE
            ppmValueLabel.visibility = View.GONE
        }
    }

    private fun setSixBandsCalc() {
        binding?.apply {
            ppmMenu.visibility = View.VISIBLE
            ppmValue.visibility = View.VISIBLE
            valueColor3.visibility = View.VISIBLE
            band3Label.visibility = View.VISIBLE
            ppmValueLabel.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}