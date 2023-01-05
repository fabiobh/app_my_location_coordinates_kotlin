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

    private lateinit var mUserViewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("__x__","___ start update fragment")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this)[LocationViewModel::class.java]

        view.updateFirstName_et.setText(args.currentUser.latitude)
        view.updateLastName_et.setText(args.currentUser.longitude)
        view.updateAge_et.setText(args.currentUser.placeName.toString())

        view.updateButton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    fun updateItem() {
        val latitude = updateFirstName_et.text.toString()
        val longitude = updateLastName_et.text.toString()
        val placeName = updateAge_et.text.toString()

        if ( inputCheck(latitude, longitude, placeName ) ) {
            val updateLocation = MyLocation(args.currentUser.id, latitude, longitude, placeName)
            mUserViewModel.updateLocation(updateLocation)
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
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteLocation(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.currentUser.placeName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Delete ${args.currentUser.placeName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.placeName}?")
        builder.create().show()
    }



}