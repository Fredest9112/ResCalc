package com.example.resistorcalc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.resistorcalc.R
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
            fromValueResistorFragment = this@FromValueResistorFragment
            setDropDownMenu(
                requireContext(), valueToleranceSelect,
                resources.getStringArray(R.array.tolerance_options)
            )
            setDropDownMenu(
                requireContext(), valuePpmSelect,
                resources.getStringArray(R.array.ppm_options)
            )
        }

        setCalcState(resCalcViewModel.noOfBands.value!!)

        binding!!.valueCalcBtn.setOnClickListener {
            if (binding!!.valueResistInput.text.toString().toInt() > 10) {
                when (resCalcViewModel.noOfBands.value) {
                    FOUR_BANDS -> {
                        setFourBandsResult()
                    }
                    FIVE_BANDS -> {
                        setFiveBandsResult()
                    }
                    SIX_BANDS -> {
                        setSixBandsResult()
                    }
                }
            } else {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.more_than_11_ohms_message),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun setSixBandsResult() {
        binding?.apply {
            resCalcViewModel.apply {
                setResultForValues(valueResistInput.text.toString())
                if (resultOfValues.size < 4) {
                    Toast.makeText(
                        context, resources.getString(R.string.less_than_5_bands_message),
                        Toast.LENGTH_LONG
                    ).show()
                    emptyResultOfValues()
                } else {
                    ColorCardView.setCardViewColor(resultOfValues[2], context, valueColor1)
                    ColorCardView.setCardViewColor(resultOfValues[1], context, valueColor2)
                    ColorCardView.setCardViewColor(resultOfValues[0], context, valueColor3)
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
    }

    private fun setFiveBandsResult() {
        binding?.apply {
            resCalcViewModel.apply {
                setResultForValues(valueResistInput.text.toString())
                if (resultOfValues.size < 4) {
                    Toast.makeText(
                        context, resources.getString(R.string.less_than_5_bands_message),
                        Toast.LENGTH_LONG
                    ).show()
                    emptyResultOfValues()
                } else {
                    ColorCardView.setCardViewColor(resultOfValues[2], context, valueColor1)
                    ColorCardView.setCardViewColor(resultOfValues[1], context, valueColor2)
                    ColorCardView.setCardViewColor(resultOfValues[0], context, valueColor3)
                    ColorCardView.setCardViewColor(resultOfValues[3], context, multiplierValue)
                    ColorCardView.setCardViewColor(
                        valueToleranceSelect.text.toString(),
                        context, valueTolerance
                    )
                    emptyResultOfValues()
                }
            }
        }
    }

    private fun setFourBandsResult() {
        binding?.apply {
            resCalcViewModel.apply {
                setResultForValues(valueResistInput.text.toString())
                ColorCardView.setCardViewColor(resultOfValues[1], context, valueColor1)
                ColorCardView.setCardViewColor(resultOfValues[0], context, valueColor2)
                ColorCardView.setCardViewColor(resultOfValues[2], context, multiplierValue)
                ColorCardView.setCardViewColor(
                    valueToleranceSelect.text.toString(),
                    context, valueTolerance
                )
                if (resultOfValues.size > 3) {
                    Toast.makeText(
                        context, resources.getString(R.string.more_than_4_bands_message),
                        Toast.LENGTH_LONG
                    ).show()
                }
                emptyResultOfValues()
            }
        }
    }

    fun setCalcState(noOfBands: Int) {
        when (noOfBands) {
            FOUR_BANDS -> {
                binding!!.valueFourBands.isChecked = true
                resCalcViewModel.setNoOfBands(FOUR_BANDS)
                setFourBandsCalc()
            }
            FIVE_BANDS -> {
                resCalcViewModel.setNoOfBands(FIVE_BANDS)
                setFiveBandsCalc()
            }
            SIX_BANDS -> {
                resCalcViewModel.setNoOfBands(SIX_BANDS)
                setSixBandsCalc()
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