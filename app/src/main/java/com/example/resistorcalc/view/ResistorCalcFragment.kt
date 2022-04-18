package com.example.resistorcalc.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.resistorcalc.R
import com.example.resistorcalc.data.InputValidator.checkInputColorToValue
import com.example.resistorcalc.data.InputValidator.isValidInput
import com.example.resistorcalc.databinding.FragmentResistorCalcBinding
import com.example.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.example.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.example.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.example.resistorcalc.model.ResCalcViewModel
import com.example.resistorcalc.view.MenuDropDownSetup.setDropDownMenu

class ResistorCalcFragment : Fragment() {

    private var binding: FragmentResistorCalcBinding? = null
    private val resCalcViewModel: ResCalcViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentResistorCalcBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        binding?.apply {
            color1Indicator
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalcState(resCalcViewModel.noOfBands.value!!)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = resCalcViewModel
            resistorCalcFragment = this@ResistorCalcFragment
            ervValueInput.setOnKeyListener { v, keyCode, _ ->
                handleKeyEvent(v, keyCode)
            }
        }

        binding?.apply {
            color1Selection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(
                    parent.adapter.getItem(position).toString(),
                    context,
                    color1Indicator
                )
                resCalcViewModel.apply {
                    setBand1(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
            }
            color2Selection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(
                    parent.adapter.getItem(position).toString(),
                    context,
                    color2Indicator
                )
                resCalcViewModel.apply {
                    setBand2(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
            }
            color3Selection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(
                    parent.adapter.getItem(position).toString(),
                    context,
                    color3Indicator
                )
                resCalcViewModel.apply {
                    setBand3(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
            }
            toleranceSelection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(
                    parent.adapter.getItem(position).toString(),
                    context,
                    toleranceIndicator
                )
                resCalcViewModel.apply {
                    setTolerance(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
            }
            multiplierSelection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(
                    parent.adapter.getItem(position).toString(),
                    context,
                    multiplierIndicator
                )
                resCalcViewModel.apply {
                    setMultiplier(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
            }
            ppmSelection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(
                    parent.adapter.getItem(position).toString(),
                    context,
                    ppmIndicator
                )
                resCalcViewModel.apply {
                    setPPM(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
            }
        }

        binding?.apply {
            ColorCardView.setCardViewColor(
                color1Selection.text.toString(),
                context,
                color1Indicator
            )
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

    fun setCalcState(noOfBands: Int) {
        when (noOfBands) {
            FOUR_BANDS -> {
                binding!!.fourBands.isChecked = true
                setFourBandsCalc()
                resCalcViewModel.setNoOfBands(FOUR_BANDS)
                resCalcViewModel.setResultForColors()
            }
            FIVE_BANDS -> {
                setFiveBandsCalc()
                resCalcViewModel.setNoOfBands(FIVE_BANDS)
                resCalcViewModel.setResultForColors()
            }
            SIX_BANDS -> {
                setSixBandsCalc()
                resCalcViewModel.setNoOfBands(SIX_BANDS)
                resCalcViewModel.setResultForColors()
            }
        }
    }

    private fun setSixBandsCalc() {
        binding?.apply {
            color3Title.visibility = View.VISIBLE
            color3Selection.visibility = View.VISIBLE
            color3Indicator.visibility = View.VISIBLE
            ppmTitle.visibility = View.VISIBLE
            ppmIndicator.visibility = View.VISIBLE
            ppmSelection.visibility = View.VISIBLE
            ppmLabel.visibility = View.VISIBLE
            ppmResult.visibility = View.VISIBLE
        }
    }

    private fun setFiveBandsCalc() {
        binding?.apply {
            color3Title.visibility = View.VISIBLE
            color3Selection.visibility = View.VISIBLE
            color3Indicator.visibility = View.VISIBLE
            ppmTitle.visibility = View.GONE
            ppmIndicator.visibility = View.GONE
            ppmSelection.visibility = View.GONE
            ppmLabel.visibility = View.GONE
            ppmResult.visibility = View.GONE
        }
    }

    private fun setFourBandsCalc() {
        binding?.apply {
            color3Title.visibility = View.GONE
            color3Selection.visibility = View.GONE
            color3Indicator.visibility = View.GONE
            ppmTitle.visibility = View.GONE
            ppmIndicator.visibility = View.GONE
            ppmSelection.visibility = View.GONE
            ppmLabel.visibility = View.GONE
            ppmResult.visibility = View.GONE
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