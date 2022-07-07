package com.itc.resistorcalc.view

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.itc.resistorcalc.data.InputValidator.checkInputValueToColor
import com.itc.resistorcalc.data.InputValidator.isValidInput
import com.itc.resistorcalc.model.ResCalcViewModel
import com.itc.resistorcalc.view.MenuDropDownSetup.setDropDownMenu
import com.itc.resistorcalc.R
import com.itc.resistorcalc.databinding.FragmentFromValueResistorBinding
import com.itc.resistorcalc.model.NoOfBands

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
            valueResistInput.setOnKeyListener { v, keyCode, _ ->
                handleKeyEvent(v, keyCode)
            }

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
                stringBand1.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueColor1)
                }
                stringBand2.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueColor2)
                }
                stringBand3.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueColor3)
                }
                stringMultiplier.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, multiplierValue)
                }
                stringTolerance.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(it.toString(), context, valueTolerance)
                }
                stringPPM.observe(viewLifecycleOwner) {
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
            resCalcViewModel.noOfBands.value!!,
            enteredText,
            activity,
            resources
        )
        if (isValidInput) {
            when (resCalcViewModel.noOfBands.value) {
                NoOfBands.FOUR_BANDS -> {
                    resCalcViewModel.setResultForValues(input)
                }
                NoOfBands.FIVE_BANDS -> {
                    resCalcViewModel.setResultForValues(input)
                }
                else -> {
                    resCalcViewModel.setResultForValues(input)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            setDropDownMenu(
                context, valueToleranceSelect,
                resources.getStringArray(R.array.tolerance_options)
            )
            setDropDownMenu(
                context, valuePpmSelect,
                resources.getStringArray(R.array.ppm_options)
            )
        }
    }

    fun setCalcState(noOfBands: Int) {
//        when (noOfBands) {
//            NoOfBands.FOUR_BANDS -> {
//                setFourBandsCalc()
//                resCalcViewModel.apply {
//                    setNoOfBands(NoOfBands.FOUR_BANDS)
//                }
//                updateResistorValues(binding?.valueResistInput?.text.toString())
//            }
//            NoOfBands.FIVE_BANDS -> {
//                setFiveBandsCalc()
//                resCalcViewModel.apply {
//                    setNoOfBands(NoOfBands.FIVE_BANDS)
//                }
//                updateResistorValues(binding?.valueResistInput?.text.toString())
//            }
//            NoOfBands.SIX_BANDS -> {
//                setSixBandsCalc()
//                resCalcViewModel.apply {
//                    setNoOfBands(NoOfBands.SIX_BANDS)
//                }
//                updateResistorValues(binding?.valueResistInput?.text.toString())
//            }
//        }
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