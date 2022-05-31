package com.itc.resistorcalc.view

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
import com.itc.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.itc.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.itc.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.itc.resistorcalc.model.ResCalcViewModel
import com.itc.resistorcalc.view.MenuDropDownSetup.setDropDownMenu

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
            resCalcViewModel.apply {
                band1.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(
                        it.toString(),
                        context,
                        color1Indicator
                    )
                }
                color1Selection.setOnItemClickListener { parent, _, position, _ ->
                    setBand1(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                band2.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(
                        it.toString(),
                        context,
                        color2Indicator
                    )
                }
                color2Selection.setOnItemClickListener { parent, _, position, _ ->
                    setBand2(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                band3.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(
                        it.toString(),
                        context,
                        color3Indicator
                    )
                }
                color3Selection.setOnItemClickListener { parent, _, position, _ ->
                    setBand3(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                multiplier.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(
                        it.toString(),
                        context,
                        multiplierIndicator
                    )
                }
                multiplierSelection.setOnItemClickListener { parent, _, position, _ ->
                    setMultiplier(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                sTolerance.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(
                        it.toString(),
                        context,
                        toleranceIndicator
                    )
                }
                toleranceSelection.setOnItemClickListener { parent, _, position, _ ->
                    setTolerance(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
                }
                sPPM.observe(viewLifecycleOwner) {
                    ColorCardView.setCardViewColor(
                        it.toString(),
                        context,
                        ppmIndicator
                    )
                }
                ppmSelection.setOnItemClickListener { parent, _, position, _ ->
                    setPPM(parent.adapter.getItem(position).toString())
                    setResultForColors()
                    setBntDetailsValidator()
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