package com.example.resistorcalc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                    setBtnValueToColorValidator()
                }
                valuePpmSelect.setOnItemClickListener { parent, _, position, _ ->
                    setPPM(parent.adapter.getItem(position).toString())
                    setBtnValueToColorValidator()
                }
            }
        }

        setCalcState(resCalcViewModel.noOfBands.value!!)

        binding!!.valueCalcBtn.setOnClickListener {
            val input = checkInputValueToColor(
                binding!!.valueResistInput,
                activity,
                resources
            )
            if (isValidInput) {
                when (resCalcViewModel.noOfBands.value) {
                    FOUR_BANDS -> {
                        setFourBandsResult(input)
                    }
                    FIVE_BANDS -> {
                        setFiveBandsResult(input)
                    }
                    SIX_BANDS -> {
                        setSixBandsResult(input)
                    }
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

    private fun setSixBandsResult(value: Long) {
        binding?.apply {
            resCalcViewModel.apply {
                setResultForValues(value)
                ColorCardView.setCardViewColor(resultOfValues[0], context, valueColor1)
                ColorCardView.setCardViewColor(resultOfValues[1], context, valueColor2)
                ColorCardView.setCardViewColor(resultOfValues[2], context, valueColor3)
                ColorCardView.setCardViewColor(resultOfValues[3], context, multiplierValue)
                ColorCardView.setCardViewColor(
                    valueToleranceSelect.text.toString(),
                    context, valueTolerance
                )
                ColorCardView.setCardViewColor(
                    valuePpmSelect.text.toString(),
                    context, ppmValue
                )
                emptyResultOfValues()

            }
        }
    }

    private fun setFiveBandsResult(value: Long) {
        binding?.apply {
            resCalcViewModel.apply {
                setResultForValues(value)
                ColorCardView.setCardViewColor(resultOfValues[0], context, valueColor1)
                ColorCardView.setCardViewColor(resultOfValues[1], context, valueColor2)
                ColorCardView.setCardViewColor(resultOfValues[2], context, valueColor3)
                ColorCardView.setCardViewColor(resultOfValues[3], context, multiplierValue)
                ColorCardView.setCardViewColor(
                    valueToleranceSelect.text.toString(),
                    context, valueTolerance
                )
                emptyResultOfValues()
            }

        }
    }

    private fun setFourBandsResult(value: Long) {
        binding?.apply {
            resCalcViewModel.apply {
                setResultForValues(value)
                ColorCardView.setCardViewColor(resultOfValues[0], context, valueColor1)
                ColorCardView.setCardViewColor(resultOfValues[1], context, valueColor2)
                ColorCardView.setCardViewColor(resultOfValues[2], context, multiplierValue)
                ColorCardView.setCardViewColor(
                    valueToleranceSelect.text.toString(),
                    context, valueTolerance
                )
                emptyResultOfValues()
            }
        }
    }

    fun setCalcState(noOfBands: Int) {
        when (noOfBands) {
            FOUR_BANDS -> {
                binding!!.valueFourBands.isChecked = true
                setFourBandsCalc()
                resCalcViewModel.apply {
                    setNoOfBands(FOUR_BANDS)
                    setBtnValueToColorValidator()
                }
            }
            FIVE_BANDS -> {
                setFiveBandsCalc()
                resCalcViewModel.apply {
                    setNoOfBands(FIVE_BANDS)
                    setBtnValueToColorValidator()
                }
            }
            SIX_BANDS -> {
                setSixBandsCalc()
                resCalcViewModel.apply {
                    setNoOfBands(SIX_BANDS)
                    setBtnValueToColorValidator()
                }
            }
        }
    }

    private fun setFourBandsCalc() {
        binding?.apply {
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