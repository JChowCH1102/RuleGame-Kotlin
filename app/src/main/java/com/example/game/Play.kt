package com.example.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_SEND
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.play.*
import kotlinx.android.synthetic.main.play.view.*
import kotlin.math.pow
import com.viniciusmo.keyboardvisibility.keyboard

class Play : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val num = ArrayList<Num>()

    var num1: String? = null
    var num2: String? = null
    var num3: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.play, container, false)
        val createSimple = createSimple()

        //RecycleView
        linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.stackFromEnd = true //display the last row
        rootView.recyclerView.layoutManager = linearLayoutManager
        rootView.recyclerView.adapter = RecycleViewAdapter(createSimple as ArrayList<Num>)

        //when open keyboard
        activity?.keyboard {
            onOpened {
                linearLayoutManager.stackFromEnd = true //display the last row
                rootView.recyclerView.layoutManager = linearLayoutManager
                rootView.recyclerView.adapter = RecycleViewAdapter(num)
            }
        }

        //check Button
        rootView.checkButton.setOnClickListener {
            result(rootView)
        }

        //the Third EditText enter button
        rootView.numberEditText3.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == IME_ACTION_SEND) {
                result(rootView)
                true
            } else {
                false
            }
        }

        // Inflate the layout for this fragment
        return rootView
    }

    //check the EditText input
    private fun result(view: View) {

        num1 = numberEditText1.text.toString()
        num2 = numberEditText2.text.toString()
        num3 = numberEditText3.text.toString()

        if (inputErrorChecking(view, numberEditText1) && inputErrorChecking(view, numberEditText2) && inputErrorChecking(view, numberEditText3)) {
            //if no input error

            //reset EditText
            numberEditText1.text.clear()
            numberEditText2.text.clear()
            numberEditText3.text.clear()
            numberEditText1.requestFocus()

            //rule
            if (num1!!.toDouble().pow(2.0) + (num2!!.toDouble().pow(2.0)) == num3!!.toDouble().pow(2.0)
            ) {
                view.recyclerView.adapter =
                    RecycleViewAdapter(addRow(num1!!, num2!!, num3!!, true) as ArrayList<Num>)
            } else {
                view.recyclerView.adapter =
                    RecycleViewAdapter(addRow(num1!!, num2!!, num3!!, false) as ArrayList<Num>)
            }
        }
    }

    private fun inputErrorChecking(view: View, editText: EditText): Boolean {
        val noInputMessage = "Please input the number"
        val errorInputMessage =
            "Please input the number without start with . or -. and end with . or -"
        val text = editText.text

        if (text.isEmpty()) {
            Toast.makeText(
                view.context,
                noInputMessage,
                Toast.LENGTH_SHORT
            ).show()
            editText.requestFocus()
            return false
        } else if (text.toString() == "-" || text.toString() == "." || text.toString() == "-.") {
            Toast.makeText(
                view.context,
                errorInputMessage,
                Toast.LENGTH_SHORT
            ).show()
            editText.text.clear()
            editText.requestFocus()
            return false
        } else if ("." in text || text.startsWith("0") || text.startsWith("-0")) {
            when (editText) {
                numberEditText1 -> num1 = changeOutput(num1!!.toDouble().toString())
                numberEditText2 -> num2 = changeOutput(num2!!.toDouble().toString())
                numberEditText3 -> num3 = changeOutput(num3!!.toDouble().toString())
            }
            return true
        } else {
            return true
        }
    }

    private fun createSimple(): List<Num> {
        num.add(Num("3", "4", "5", true))
        return num
    }

    private fun addRow(row1: String, row2: String, row3: String, rowToF: Boolean): List<Num> {
        num.add(Num(row1, row2, row3, rowToF))
        return num
    }

    private fun changeOutput(numString: String): String {
        return if (numString == "0.0" || numString == "-0.0") {
            "0"
        } else if (numString.endsWith(".0") ) {
            numString.replace(".0", "")
        } else {
            numString
        }
    }

}
