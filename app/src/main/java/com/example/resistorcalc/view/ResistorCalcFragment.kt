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
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.resistorcalc.R
import com.example.resistorcalc.databinding.FragmentResistorCalcBinding
import com.example.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.example.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.example.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.example.resistorcalc.model.ResCalcViewModel
import com.example.resistorcalc.view.MenuDropDownSetup.setDropDownMenu

class ResistorCalcFragment : Fragment() {

    private var binding:FragmentResistorCalcBinding? = null
    private val resCalcViewModel : ResCalcViewModel by activityViewModels()

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
            setDropDownMenu(requireContext(), color1Selection, resources.getStringArray(R.array.color_options))
            setDropDownMenu(requireContext(), color2Selection, resources.getStringArray(R.array.color_options))
            setDropDownMenu(requireContext(), color3Selection, resources.getStringArray(R.array.color_options))
            setDropDownMenu(requireContext(), multiplierSelection, resources.getStringArray(R.array.multiplier_options))
            setDropDownMenu(requireContext(), toleranceSelection, resources.getStringArray(R.array.tolerance_options))
            setDropDownMenu(requireContext(), ppmSelection, resources.getStringArray(R.array.ppm_options))
        }

        resCalcViewModel.noOfBands.value?.let { setCalcState(it) }

        binding?.apply {
            color1Selection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(parent.adapter.getItem(position).toString(), context, color1Indicator)
                resCalcViewModel.apply {
                    setBand1(parent.adapter.getItem(position).toString())
                    setResult()
                }
            }
            color2Selection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(parent.adapter.getItem(position).toString(), context, color2Indicator)
                resCalcViewModel.apply {
                    setBand2(parent.adapter.getItem(position).toString())
                    setResult()
                }
            }
            color3Selection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(parent.adapter.getItem(position).toString(), context, color3Indicator)
                resCalcViewModel.apply {
                    setBand3(parent.adapter.getItem(position).toString())
                    setResult()
                }
            }
            toleranceSelection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(parent.adapter.getItem(position).toString(), context, toleranceIndicator)
                resCalcViewModel.apply {
                    setTolerance(parent.adapter.getItem(position).toString())
                    setResult()
                }
            }
            multiplierSelection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(parent.adapter.getItem(position).toString(), context, multiplierIndicator)
                resCalcViewModel.apply {
                    setMultiplier(parent.adapter.getItem(position).toString())
                    setResult()
                }
            }
            ppmSelection.setOnItemClickListener { parent, _, position, _ ->
                ColorCardView.setCardViewColor(parent.adapter.getItem(position).toString(), context, ppmIndicator)
                resCalcViewModel.apply {
                    setPPM(parent.adapter.getItem(position).toString())
                    setResult()
                }
            }
        }
    }

    fun goToDetailsScreen() {
        binding?.apply {
            if(ervValueInput.text.toString().isNotEmpty()){
                val action = ResistorCalcFragmentDirections
                    .actionResistorCalcFragmentToResistorDetailsFragment(
                        ervValue = ervValueInput.text.toString().toFloat())
                findNavController().navigate(action)
                resCalcViewModel.setMaxVal()
                resCalcViewModel.setMinVal()
            } else{
                Toast.makeText(activity, resources.getString(R.string.erv_empty_value), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun setCalcState(noOfBands:Int){
        when(noOfBands) {
            FOUR_BANDS ->{
                binding!!.fourBands.isChecked = true
                setFourBandsCalc()
                resCalcViewModel.setNoOfBands(FOUR_BANDS)
                resCalcViewModel.setResult()
            }
            FIVE_BANDS ->{
                setFiveBandsCalc()
                resCalcViewModel.setNoOfBands(FIVE_BANDS)
                resCalcViewModel.setResult()
            }
            SIX_BANDS ->{
                setSixBandsCalc()
                resCalcViewModel.setNoOfBands(SIX_BANDS)
                resCalcViewModel.setResult()
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

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean{
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return true
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}