package com.itc.resistorcalc.view.fromvalueresistor

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.itc.resistorcalc.MyApp
import com.itc.resistorcalc.viewutils.InputValidator.checkInputValueToColor
import com.itc.resistorcalc.viewutils.InputValidator.isValidInput
import com.itc.resistorcalc.viewutils.MenuDropDownSetup.setDropDownMenu
import com.itc.resistorcalc.R
import com.itc.resistorcalc.data.NoOfBands
import com.itc.resistorcalc.databinding.FragmentFromValueResistorBinding
import com.itc.resistorcalc.model.fromvalueresistor.FromValueResCalcViewModel
import com.itc.resistorcalc.model.fromvalueresistor.FromValueResCalcViewModelFactory
import javax.inject.Inject

class FromValueResistorFragment : Fragment() {

    private var binding: FragmentFromValueResistorBinding? = null

    @Inject
    lateinit var fromValueResCalcViewModelFactory: FromValueResCalcViewModelFactory

    private val fromValueResCalcViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            fromValueResCalcViewModelFactory
        )[FromValueResCalcViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as MyApp).appComponent.injectFromValueResistorFragment(
            this
        )
    }

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
            viewModel = fromValueResCalcViewModel
            lifecycleOwner = viewLifecycleOwner
            fromValueResistorFragment = this@FromValueResistorFragment
            valueResistInput.setOnKeyListener { v, keyCode, _ ->
                handleKeyEvent(v, keyCode)
            }
        }

        binding?.apply {
            fromValueResCalcViewModel.apply {

                resistResult.value?.let { valueResistInput.setText(it) }
                valueBandOptions.check(getNoOfBands())
                valueToleranceSelect.setText(stringTolerance.value)

                valueToleranceSelect.setOnItemClickListener { parent, _, position, _ ->
                    setTolerance(parent.adapter.getItem(position).toString())
                }
                valuePpmSelect.setOnItemClickListener { parent, _, position, _ ->
                    setPPM(parent.adapter.getItem(position).toString())
                }

                valueResistInput.doOnTextChanged { enteredText, _, _, _ ->
                    updateResistorValues(enteredText.toString())
                    setInput(enteredText.toString())
                }

                valueBandOptions.setOnCheckedChangeListener { radioGroup, _ ->
                    when (radioGroup.checkedRadioButtonId) {
                        R.id.value_4_bands -> {
                            setNoOfBands(4)
                            updateResistorValues(valueResistInput.text.toString())
                        }
                        R.id.value_5_bands -> {
                            setNoOfBands(5)
                            updateResistorValues(valueResistInput.text.toString())
                        }
                        R.id.value_6_bands -> {
                            setNoOfBands(6)
                            updateResistorValues(valueResistInput.text.toString())
                        }
                    }
                }
            }
        }
    }

    private fun getNoOfBands(): Int {
        return when(fromValueResCalcViewModel.noOfBands.value){
            NoOfBands.FOUR_BANDS -> R.id.value_4_bands
            NoOfBands.FIVE_BANDS -> R.id.value_5_bands
            else -> R.id.value_6_bands
        }
    }

    private fun updateResistorValues(enteredText: String) {
        val input = checkInputValueToColor(
            fromValueResCalcViewModel.noOfBands.value!!,
            enteredText,
            activity,
            resources
        )
        if (isValidInput) {
            when (fromValueResCalcViewModel.noOfBands.value) {
                NoOfBands.FOUR_BANDS -> {
                    fromValueResCalcViewModel.setResultForValues(input)
                }
                NoOfBands.FIVE_BANDS -> {
                    fromValueResCalcViewModel.setResultForValues(input)
                }
                else -> {
                    fromValueResCalcViewModel.setResultForValues(input)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            setDropDownMenu(
                context, valueToleranceSelect,
                resources.getStringArray(R.array.tolerance_numeric_options)
            )
            setDropDownMenu(
                context, valuePpmSelect,
                resources.getStringArray(R.array.ppm_numeric_options)
            )
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