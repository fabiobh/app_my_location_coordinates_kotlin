package com.uaialternativa.mycoordinateslocation.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.uaialternativa.mycoordinateslocation.R
import com.uaialternativa.mycoordinateslocation.model.MyLocation
import com.uaialternativa.mycoordinateslocation.util.dprint
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var locationList = emptyList<MyLocation>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = locationList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.latitude_txt.text = currentItem.latitude.toString()
        holder.itemView.longitude_txt.text = currentItem.longitude.toString()
        holder.itemView.placeName_txt.text = currentItem.placeName.toString()

        dprint("holder.itemView.emptyTextMessage.text.toString()")
        dprint(holder.itemView.emptyTextMessage?.text.toString())
        dprint("holder.itemView.age_txt.text")
        dprint(holder.itemView.placeName_txt?.text.toString())


        /*
        if ( locationList.size != 0 ) {
            holder.itemView.emptyTextMessage.visibility = View.GONE
        }
        */

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(location: List<MyLocation>) {
        this.locationList = location
        notifyDataSetChanged()
    }

}