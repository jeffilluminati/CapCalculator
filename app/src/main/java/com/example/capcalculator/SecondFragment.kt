package com.example.capcalculator

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.size
import androidx.navigation.fragment.findNavController

// This is the Second Fragment where the input for the different EditTexts for the CAP Input is handled
// and then the CAP output is calculated, which will then be passed to ThirdFragment.

class SecondFragment : Fragment() {
    private var param: Array<String?>? = null
    private var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        param = arguments?.getStringArray("subjectList")


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayout = view.findViewById<LinearLayout>(R.id.LinearLayout)

        for (s in param!!) {
            createField(s!!, linearLayout!!)
        }

        view.findViewById<Button>(R.id.submitButton).setOnClickListener {
            onClick()
        }


        linearLayout?.get(linearLayout?.size!! - 1)?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                onClick()
                return@OnKeyListener true
            }

            return@OnKeyListener false

        })

    }

    private fun onClick() {
        if ((linearLayout?.children as Sequence<EditText>).all {
                    it -> it.text.isNotEmpty() && 0 < it.text.toString().toFloat() && it.text.toString().toFloat() <= 5
                }) {
            val CAP = getCap()
            val bundle = bundleOf("CAP" to CAP)
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)
        } else {
            val dialogBuilder = AlertDialog.Builder(this.context)

            dialogBuilder.setMessage("Please input information correctly")
                    .setCancelable(false)
                    .setPositiveButton("Ok", {
                        dialog, id -> dialog.cancel()
                    })

            val alert = dialogBuilder.create()

            alert.setTitle("Error!")

            alert.show()
        }
    }

    private fun createField(subject: String, parent: LinearLayout) {
        val editText = EditText(this.context)

        editText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        editText.hint = "$subject CAP"
        editText.inputType = InputType.TYPE_CLASS_NUMBER.or(InputType.TYPE_NUMBER_FLAG_DECIMAL)
        editText.maxLines=1
        editText.width = 600

        parent.addView(editText)

    }

    private fun getCap(): Float {
        var credit_sum = 0.0F
        var cumulative_sum = 0.0F
        for (i in 0..(param?.size?.minus(1) ?: throw Error())) {
            val s = param!!.get(i)
            val credit = when (s) {
                "Math" -> 10
                "English" -> 6
                "Chemistry" -> 8
                "Biology" -> 8
                "Physics" -> 8
                "Computing" -> 8
                "Mother Tongue" -> 8
                "History" -> 4
                "Geography" -> 4
                "Art" -> 4
                "Music" -> 4
                else -> 0
            }

            credit_sum += credit

            val cap = (linearLayout?.children as Sequence<EditText>).toList().get(i).text.toString()
                    .toFloat()
            cumulative_sum += cap*credit

        }

        return (cumulative_sum/credit_sum)
    }
}