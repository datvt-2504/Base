package com.example.calculator.view.calculator

import android.view.View
import com.example.calculator.R
import com.example.calculator.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_calculator2.*


class CalculatorFragment : BaseFragment(R.layout.fragment_calculator2), View.OnClickListener {

    private var result = ""
    private var operator = ""
    private var temp = ""

    override fun onCreatedView() {
        initOnClick()
    }

    override fun onBackPress(): Boolean {
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button0 -> numberOnClick(getString(R.string.zero))
            R.id.button1 -> numberOnClick(getString(R.string.one))
            R.id.button2 -> numberOnClick(getString(R.string.two))
            R.id.button3 -> numberOnClick(getString(R.string.three))
            R.id.button4 -> numberOnClick(getString(R.string.four))
            R.id.button5 -> numberOnClick(getString(R.string.five))
            R.id.button6 -> numberOnClick(getString(R.string.six))
            R.id.button7 -> numberOnClick(getString(R.string.seven))
            R.id.button8 -> numberOnClick(getString(R.string.eight))
            R.id.button9 -> numberOnClick(getString(R.string.nine))
            R.id.buttonClear -> clear()
            R.id.buttonPlus -> operatorOnClick(getString(R.string.plus))
            R.id.buttonMinus -> operatorOnClick(getString(R.string.minus))
            R.id.buttonMultiply -> operatorOnClick(getString(R.string.multiply))
            R.id.buttonDivide -> operatorOnClick(getString(R.string.divide))
            R.id.buttonEqual -> calculate()
        }
    }

    private fun initOnClick() {
        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonPlus.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
    }

    private fun numberOnClick(number: String) {
        result = textResult.text.toString()
        result += number
        textResult.text = result
    }

    private fun operatorOnClick(operator: String) {
        temp = textResult.text.toString()
        clear()
        this.operator = operator
    }

    private fun clear() {
        result = ""
        textResult.text = ""
    }

    private fun calculate() {
        var res = 0.0
        try {
            val numberOld = this.temp.toDouble()
            val numberNew = textResult.text.toString().toDouble()
            res = when (operator) {
                getString(R.string.plus) -> numberOld + numberNew
                getString(R.string.minus) -> numberOld - numberNew
                getString(R.string.multiply) -> numberOld * numberNew
                getString(R.string.divide) -> numberOld / numberNew
                else -> res
            }
            result = res.toString()
            textResult.text = result
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
