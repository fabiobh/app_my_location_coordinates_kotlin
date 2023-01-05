package com.uaialternativa.mycoordinateslocation.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.uaialternativa.mycoordinateslocation.R
import com.uaialternativa.mycoordinateslocation.model.MyLocation
import com.uaialternativa.mycoordinateslocation.util.dprint
import com.uaialternativa.mycoordinateslocation.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mLocationViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("__x__","___ start update fragment")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mLocationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]

        view.updateLatitude_et.setText(args.currentLocation.latitude)
        view.updateLongitude_et.setText(args.currentLocation.longitude)
        view.updatePlaceName_et.setText(args.currentLocation.placeName.toString())

        view.updateButton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    fun updateItem() {
        val latitude = updateLatitude_et.text.toString()
        val longitude = updateLongitude_et.text.toString()
        val placeName = updatePlaceName_et.text.toString()

        if ( inputCheck(latitude, longitude, placeName ) ) {
            val updateLocation = MyLocation(args.currentLocation.id, latitude, longitude, placeName)
            mLocationViewModel.updateLocation(updateLocation)
            Toast.makeText(requireContext(), "Location succesfully updated", Toast.LENGTH_LONG ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill out all fields", Toast.LENGTH_LONG).show()
        }

    }


    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !( TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age) )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        dprint("item id")
        dprint(item.itemId.toString())

        if (item.itemId == android.R.id.home) {
            dprint("back arrow clicked")
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        if (item.itemId == R.id.menu_delete) {
            deleteLocation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteLocation() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mLocationViewModel.deleteLocation(args.currentLocation)
            Toast.makeText(
                requireContext(),
                "Successfully removed \"${args.currentLocation.latitude}\"",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Delete \"${args.currentLocation.latitude}\"?")
        builder.setMessage("Are you sure you want to delete \"${args.currentLocation.latitude}\"?")
        builder.create().show()
    }



}