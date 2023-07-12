package com.nayanthayasiru.simple_calculator.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.nayanthayasiru.simple_calculator.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        val message = view?.findViewById<TextView>(R.id.message)
        // result text
        message?.text = viewModel.result.value.toString()
        // observer
        viewModel.result.observe(viewLifecycleOwner) { message?.text = it.toString() }
        // buttons
        val addButton = view?.findViewById<Button>(R.id.addButton)
        val subtractButton = view?.findViewById<Button>(R.id.subtractButton)
        val multiplyButton = view?.findViewById<Button>(R.id.multiplyButton)
        val divideButton = view?.findViewById<Button>(R.id.divideButton)
        // number inputs
        val numberOneEditText = view?.findViewById<EditText>(R.id.numberOne)
        val numberTwoEditText = view?.findViewById<EditText>(R.id.numberTwo)
        // click listeners
        addButton?.setOnClickListener {
            val numberOne = numberOneEditText?.text.toString()
            val numberTwo = numberTwoEditText?.text.toString()
            if (validateInputs(numberOne, numberTwo)){
                viewModel.add(numberOne.toDouble(), numberTwo.toDouble())
            }
        }
        subtractButton?.setOnClickListener {
            val numberOne = numberOneEditText?.text.toString()
            val numberTwo = numberTwoEditText?.text.toString()
            if (validateInputs(numberOne, numberTwo)){
                viewModel.subtract(numberOne.toDouble(), numberTwo.toDouble())
            }
        }
        multiplyButton?.setOnClickListener {
            val numberOne = numberOneEditText?.text.toString()
            val numberTwo = numberTwoEditText?.text.toString()
            if (validateInputs(numberOne, numberTwo)){
                viewModel.multiply(numberOne.toDouble(), numberTwo.toDouble())
            }
        }
        divideButton?.setOnClickListener {
            val numberOne = numberOneEditText?.text.toString()
            val numberTwo = numberTwoEditText?.text.toString()
            if (validateInputs(numberOne, numberTwo) and ! num2IsZero(numberTwo)){
                viewModel.divide(numberOne.toDouble(), numberTwo.toDouble())
            }
        }
        return view
    }
    private fun validateInputs(num1:String?, num2:String?):Boolean{
        return when {
            num1.isNullOrEmpty() and num2.isNullOrEmpty() ->{
                Toast.makeText(requireActivity(), "Both Inputs are Empty.", Toast.LENGTH_SHORT).show()
                false
            }
            num1.isNullOrEmpty()->{
                Toast.makeText(requireActivity(), "First Input is Empty.", Toast.LENGTH_SHORT).show()
                false
            }
            num2.isNullOrEmpty()->{
                Toast.makeText(requireActivity(), "Second Input is Empty.", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

    private fun num2IsZero(num2:String?):Boolean{
        return when (num2) {
            "0" -> {
                Toast.makeText(requireActivity(), "Second Input cannot be 0.", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                false
            }
        }
    }
}