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
import androidx.navigation.fragment.findNavController
import com.example.resistorcalc.R
import com.example.resistorcalc.databinding.FragmentResistorCalcBinding
import com.example.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.example.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.example.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.example.resistorcalc.model.ResCalcViewModel

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
            ervValue.setOnKeyListener { v, keyCode, _ ->
                handleKeyEvent(v, keyCode)
            }
        }
        resCalcViewModel.noOfBands.value?.let { setCalcState(it) }
    }

    fun goToDetailsScreen() {
        binding?.apply {
            if(ervValue.text.toString().isNotEmpty()){
                val action = ResistorCalcFragmentDirections
                    .actionResistorCalcFragmentToResistorDetailsFragment(
                        ervValue = ervValue.text.toString().toFloat())
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

    fun setValues(){
        binding?.apply {
            resCalcViewModel.apply {
                setBand1(color1Selection.selectedItem.toString())
                ColorCardView().setCardViewColor(color1Selection.selectedItem.toString(),
                    context, color1Indicator)
                setBand2(color2Selection.selectedItem.toString())
                ColorCardView().setCardViewColor(color2Selection.selectedItem.toString(),
                    context, color2Indicator)
                setBand3(color3Selection.selectedItem.toString())
                ColorCardView().setCardViewColor(color3Selection.selectedItem.toString(),
                    context, color3Indicator)
                setMultiplier(multiplierSelection.selectedItem.toString())
                ColorCardView().setCardViewColor(multiplierSelection.selectedItem.toString(),
                    context, multiplierIndicator)
                setTolerance(toleranceSelection.selectedItem.toString())
                ColorCardView().setCardViewColor(toleranceSelection.selectedItem.toString(),
                    context, toleranceIndicator)
                setPPM(ppmSelection.selectedItem.toString())
                ColorCardView().setCardViewColor(ppmSelection.selectedItem.toString(),
                    context, ppmIndicator)
                setResult()
            }
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