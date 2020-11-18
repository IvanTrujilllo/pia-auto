package com.example.barbershop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast

class ServiciosActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var gridView:GridView ? = null
    private var arrayList:ArrayList<CortesItem> ? = null
    private var cortesAdapter:CortesAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        gridView = findViewById(R.id.myGrid)
        arrayList = ArrayList()
        arrayList = setDataList()
        cortesAdapter = CortesAdapter(applicationContext, arrayList!!)
        gridView?.adapter = cortesAdapter
        gridView?.onItemClickListener = this
    }

    private fun setDataList(): ArrayList<CortesItem>{

        var arrayList: ArrayList<CortesItem> = ArrayList()

        arrayList.add(CortesItem(R.drawable.corte1, "Burst taper"))
        arrayList.add(CortesItem(R.drawable.corte2, "jelly roll"))
        arrayList.add(CortesItem(R.drawable.corte3, "Slicked"))
        arrayList.add(CortesItem(R.drawable.corte4, "Low fade"))
        arrayList.add(CortesItem(R.drawable.corte5, "Blurry fade"))
        arrayList.add(CortesItem(R.drawable.corte6, "Fade"))

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var cortesItem:CortesItem = arrayList!!.get(position)
        Toast.makeText(applicationContext, cortesItem.name, Toast.LENGTH_LONG).show()
    }
}