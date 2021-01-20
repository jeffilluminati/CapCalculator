package com.example.capcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// This is the Third Fragment, which simply has one TextView.
// The CAP from second fragment will be received via navigation argument and will then be displayed
// on the TextView

class ThirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var CAP = arguments?.getFloat("CAP")

        view?.findViewById<TextView>(R.id.CAPView)?.setText(String.format("Your CAP is %.1f", CAP))
    }

}