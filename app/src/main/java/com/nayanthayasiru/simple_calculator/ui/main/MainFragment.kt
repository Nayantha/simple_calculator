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
        message?.text = viewModel.result.value.toString()
        viewModel.result.observe(viewLifecycleOwner) { message?.text = it.toString() }
        val addButton = view?.findViewById<Button>(R.id.addButton)
        val numberOneEditText = view?.findViewById<EditText>(R.id.numberOne)
        val numberTwoEditText = view?.findViewById<EditText>(R.id.numberTwo)
        addButton?.setOnClickListener {
            val numberOne = numberOneEditText?.text.toString()
            val numberTwo = numberTwoEditText?.text.toString()
            if (validateInputs(numberOne, numberTwo)){
                viewModel.add(numberOne.toDouble(), numberTwo.toDouble())
            }
        }
        return view
    }
    private fun validateInputs(num1:String?, num2:String?):Boolean{
        return when {
            num1.isNullOrEmpty() and num2.isNullOrEmpty() ->{
                Toast.makeText(requireActivity(), "Both Inputs are Empty", Toast.LENGTH_SHORT).show()
                false
            }
            num1.isNullOrEmpty()->{
                Toast.makeText(requireActivity(), "First Input is Empty", Toast.LENGTH_SHORT).show()
                false
            }
            num2.isNullOrEmpty()->{
                Toast.makeText(requireActivity(), "Second Input is Empty", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

}