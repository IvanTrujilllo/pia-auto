package com.example.barbershop

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.security.AccessControlContext

class CortesAdapter(var context: Context, var arrayList: ArrayList<CortesItem>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View = View.inflate(context, R.layout.grid_item_list, null)

        var cortes: ImageView = view.findViewById(R.id.corte1)
        var name: TextView = view.findViewById(R.id.texto1)

        var CortesItem: CortesItem = arrayList.get(position)

        cortes.setImageResource(CortesItem.cortes!!)
        name.text = CortesItem.name

        return view
    }


}