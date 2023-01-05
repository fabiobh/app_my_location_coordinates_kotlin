package com.uaialternativa.mycoordinateslocation.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.uaialternativa.mycoordinateslocation.R
import com.uaialternativa.mycoordinateslocation.model.MyLocation
import com.uaialternativa.mycoordinateslocation.viewmodel.LocationViewModel
import com.uaialternativa.mycoordinateslocation.util.dprint
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mLocationViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("__x__","___ start add fragment")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mLocationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]

        view.addButton.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        dprint("insertDataToDatabase")
        val firstName: String = addFirstName_et.text.toString()
        val lastName: String = addLastName_et.text.toString()
        val age: String = addAge_et.text.toString()

        if(inputCheck(firstName, lastName, age)) {
            // create location object
            val location = MyLocation( 0, firstName, lastName, age )
            mLocationViewModel.addLocation(location)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !( TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age) )
    }

}