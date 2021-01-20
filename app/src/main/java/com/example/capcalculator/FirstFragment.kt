package com.example.capcalculator

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import java.io.FileOutputStream
import java.io.OutputStreamWriter

// This Fragment is the start screen, where the different subjects and the choices are displayed
// The various subjects selected will then be passed in a String array via Navigation Bundle to
// Second Navigation

class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val list = ArrayList<String>()

            if (view?.findViewById<CheckBox>(R.id.mathBox)?.isChecked!!)
                list.add("Math")
            if (view?.findViewById<CheckBox>(R.id.englishBox)?.isChecked!!)
                list.add("English")
            if (view?.findViewById<CheckBox>(R.id.chemistryBox)?.isChecked!!)
                list.add("Chemistry")
            if (view?.findViewById<CheckBox>(R.id.physicsBox)?.isChecked!!)
                list.add("Physics")
            if (view?.findViewById<CheckBox>(R.id.computingBox)?.isChecked!!)
                list.add("Computing Studies")
            if (view?.findViewById<CheckBox>(R.id.motherTongueBox)?.isChecked!!)
                list.add("Mother Tongue")
            if (view?.findViewById<CheckBox>(R.id.artBox)?.isChecked!!)
                list.add("Art")
            if (view?.findViewById<CheckBox>(R.id.musicBox)?.isChecked!!)
                list.add("Music")
            if (view?.findViewById<CheckBox>(R.id.geographyBox)?.isChecked!!)
                list.add("Geography")
            if (view?.findViewById<CheckBox>(R.id.historyBox)?.isChecked!!)
                list.add("History")

            val subjectList: Array<String?> = arrayOfNulls<String>(list.size)
            list.toArray(subjectList)

            val bundle = bundleOf("subjectList" to subjectList)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }


    }

}